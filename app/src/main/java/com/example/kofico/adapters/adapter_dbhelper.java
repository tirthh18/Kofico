package com.example.kofico.adapters;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.kofico.models.ItemUtils;
import com.example.kofico.models.item_home;
import com.example.kofico.models.item_order;
import com.example.kofico.models.user;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class adapter_dbhelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Kofico.db";

    public adapter_dbhelper(Context context) {
        super(context, DBNAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE USER(userid INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT UNIQUE, name TEXT, password TEXT, email TEXT, phone TEXT)");
        MyDB.execSQL("CREATE TABLE CART(userid INTEGER, itemid INTEGER, quantity INTEGER, FOREIGN KEY(userid) REFERENCES USER(userid))");
        MyDB.execSQL("CREATE TABLE ORDERS(order_id INTEGER PRIMARY KEY AUTOINCREMENT,userid INTEGER,quantity TEXT,total INTAGER,order_date DATE DEFAULT (DATE('now')),FOREIGN KEY(userid) REFERENCES USER(userid))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("DROP TABLE IF EXISTS USER");
        MyDB.execSQL("DROP TABLE IF EXISTS CART");
        MyDB.execSQL("DROP TABLE IF EXISTS ORDERS");
        onCreate(MyDB);
    }

    public boolean insertData(String username, String name, String password, String email) {
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("name", name);
        contentValues.put("password", password);
        contentValues.put("email", email);
        long result = mydb.insert("USER", null, contentValues);
        return result != -1;
    }

    public user getUserDetails(String username) {
        SQLiteDatabase mydb = this.getReadableDatabase();
        Cursor cursor = mydb.rawQuery("SELECT * FROM USER WHERE username=?", new String[]{username});

        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") int userid = cursor.getInt(cursor.getColumnIndex("userid"));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex("email"));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));
            cursor.close();

            return new user(userid, username, name, password, email);
        } else {
            Log.d("DBHelper", "no user found for username: " + username);
            return null;
        }
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase mydb = this.getReadableDatabase();
        Cursor cursor = mydb.rawQuery("SELECT * FROM USER WHERE username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            cursor.close();
            return true;
        } else {
            cursor.close();
            return false;
        }
    }

    public boolean checkUserLogin(String username, String password) {
        SQLiteDatabase mydb = this.getReadableDatabase();
        Cursor cur = mydb.rawQuery("SELECT * FROM USER WHERE username=? AND password=?", new String[]{username, password});
        return cur.getCount() > 0;
    }

    public int getUserId(String username) {
        SQLiteDatabase mydb = this.getReadableDatabase();
        Cursor cursor = mydb.rawQuery("SELECT userid FROM USER WHERE username = ?", new String[]{username});
        if (cursor != null && cursor.moveToFirst()) {
            @SuppressLint("Range") int userid = cursor.getInt(cursor.getColumnIndex("userid"));
            cursor.close();
            return userid;
        } else {
            cursor.close();
            return -1;
        }
    }



    // cartdata related operation
        public boolean insertCartData(int userId, int itemId, int change) {
            SQLiteDatabase mydb = this.getWritableDatabase();
            Cursor cursor = mydb.rawQuery("SELECT quantity FROM CART WHERE userid=? AND itemid=?",
                    new String[]{String.valueOf(userId), String.valueOf(itemId)});

            if (cursor != null && cursor.moveToFirst()) {
                @SuppressLint("Range") int currentQuantity = cursor.getInt(cursor.getColumnIndex("quantity"));
                int newQuantity = currentQuantity + change;

                if (newQuantity > 0) {
                    ContentValues values = new ContentValues();
                    values.put("quantity", newQuantity);
                    mydb.update("CART", values, "userid=? AND itemid=?", new String[]{String.valueOf(userId), String.valueOf(itemId)});
                } else {
                    mydb.delete("CART", "userid=? AND itemid=?", new String[]{String.valueOf(userId), String.valueOf(itemId)});
                }
            } else {
                // Item does not exist, insert new record
                if (change > 0) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("userid", userId);
                    contentValues.put("itemid", itemId);
                    contentValues.put("quantity", change);
                    long result = mydb.insert("CART", null, contentValues);
                    if (result == -1) {
                        // Insert failed
                        return false;
                    }
                }
            }
            cursor.close();
            return true;
        }



        public List<Map.Entry<item_home, Integer>> getSortedCartItems(int userid) {
        SQLiteDatabase mydb = this.getReadableDatabase();
        Cursor cursor = mydb.rawQuery("SELECT * FROM CART WHERE userid=?", new String[]{String.valueOf(userid)});
        Map<item_home, Integer> cartItems = new LinkedHashMap<>();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int itemid = cursor.getInt(cursor.getColumnIndex("itemid"));
                @SuppressLint("Range") int quantity = cursor.getInt(cursor.getColumnIndex("quantity"));
                item_home item = ItemUtils.fetchItemById(itemid);
                if (item != null) {
                    cartItems.put(item, quantity);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        List<Map.Entry<item_home, Integer>> sortedItems = new ArrayList<>(cartItems.entrySet());
        sortedItems.sort((entry1, entry2) -> entry1.getKey().getTitle().compareTo(entry2.getKey().getTitle()));
        return sortedItems;
    }

    public void clearCartForUser(int userid) {
        SQLiteDatabase mydb = this.getWritableDatabase();
        mydb.delete("CART", "userid=?", new String[]{String.valueOf(userid)});
    }



    // orderdata related operation
    public boolean insertOrder(int userid,String total) {
        int quantity = getTotalCartQuantity(userid);
        if (quantity > 0) {
            SQLiteDatabase mydb = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("userid", userid);
            contentValues.put("quantity", quantity);
            contentValues.put("total", total);
            long result = mydb.insert("ORDERS", null, contentValues);
            return result != -1;
        } else {
            return false;
        }
    }

    @SuppressLint("Range")
    public int getTotalCartQuantity(int userid) {
        SQLiteDatabase mydb = this.getReadableDatabase();
        Cursor cursor = mydb.rawQuery("SELECT SUM(quantity) AS total_quantity FROM CART WHERE userid = ?", new String[]{String.valueOf(userid)});
        int totalQuantity = 0;
        if (cursor != null && cursor.moveToFirst()) {
            totalQuantity = cursor.getInt(cursor.getColumnIndex("total_quantity"));
        }
            cursor.close();
        return totalQuantity;
    }


    public List<item_order> getUserOrders(int userid) {
        SQLiteDatabase mydb = this.getReadableDatabase();
        Cursor cursor = mydb.rawQuery("SELECT quantity, total, order_date FROM ORDERS WHERE userid=?",new String[]{String.valueOf(userid)});

        List<item_order> orderList = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int quantity = cursor.getInt(cursor.getColumnIndex("quantity"));
                @SuppressLint("Range") String total = cursor.getString(cursor.getColumnIndex("total"));
                @SuppressLint("Range") String orderDate = cursor.getString(cursor.getColumnIndex("order_date"));

                orderList.add(new item_order(quantity, total, orderDate));
            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }

        return orderList;
    }


}
