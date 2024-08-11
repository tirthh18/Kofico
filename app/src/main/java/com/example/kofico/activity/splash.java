package com.example.kofico.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kofico.R;

public class splash extends AppCompatActivity {
    Handler handler = new Handler();
    Runnable runnable ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);
       runnable = new Runnable(){
           @Override
           public void run(){
               Intent inte = new Intent(splash.this, login.class);
               startActivity(inte);
               finish();
           }
       };
       handler.postDelayed(runnable,6000);
    }
    @Override
    protected void onDestroy() {

        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}