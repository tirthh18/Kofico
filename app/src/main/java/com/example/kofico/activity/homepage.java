package com.example.kofico.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.kofico.R;

public class homepage extends AppCompatActivity {
//    RecyclerView allitem;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_homepage);

//        allitem = findViewById(R.id.rv_all_item);
//        ArrayList<item> list = new ArrayList<>();
//        list.add(new item("hii", "hoo", "hoo", R.mipmap.kopi1));
//        adapter_item d = new adapter_item(list, this);
//        allitem.setAdapter(d);
    }

}