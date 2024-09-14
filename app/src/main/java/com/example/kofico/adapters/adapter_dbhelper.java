package com.example.kofico.adapters;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.kofico.models.user;

public class adapter_dbhelper extends SQLiteOpenHelper {
    public static final String DBNAME = "Login.db";

    public adapter_dbhelper(Context context) {
        super(context, "Login.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("CREATE TABLE USER(username TEXT primary key,name TEXT,password TEXT,email TEXT,phone TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop table if exists user");
        onCreate(MyDB);
    }

    public boolean insertData(String username, String name, String password, String email) {
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("name", name);
        contentValues.put("password", password);
        contentValues.put("email", email);
        long result = mydb.insert("user", null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean checkusername(String username, String password) {
        SQLiteDatabase mydb = this.getWritableDatabase();
        Cursor cur = mydb.rawQuery("SELECT * FROM USER where username=? and password=?", new String[]{username, password});
        if (cur.getCount() > 0) {
            return true;
        } else
            return false;
    }

    public user getUserDetails(String uname) {
        SQLiteDatabase mydb = this.getReadableDatabase();
        Log.d("Database", "Querying user with username: " + uname);
        Cursor cur = mydb.rawQuery("SELECT * FROM USER WHERE username=?", new String[]{uname});

        if (cur != null && cur.moveToFirst()) {
//            for (String columnName : cur.getColumnNames()) {
//                int columnIndex = cur.getColumnIndex(columnName);
//                Log.d("Database", "Column: " + columnName + " Index: " + columnIndex);
//            }
            int usernameIndex = cur.getColumnIndex("username");
            int nameIndex = cur.getColumnIndex("name");
            int passwordIndex = cur.getColumnIndex("password");
            int emailIndex = cur.getColumnIndex("email");

            if (usernameIndex != -1 && nameIndex != -1 && passwordIndex != -1 && emailIndex != -1 ) {
                user user = new user(
                        cur.getString(usernameIndex),
                        cur.getString(nameIndex),
                        cur.getString(passwordIndex),
                        cur.getString(emailIndex)
                );
                cur.close();
                return user;
            } else {
                Log.d("Database", "User not found for username: " + uname);
                cur.close();
                return null;
            }
        } else {
            Log.d("Database", "Cursor is null for username: " + uname);
            return null;
        }
    }

}

