package com.example.kofico.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kofico.R;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        Button login=(Button) findViewById(R.id.login_button_login);
        TextView register=(TextView) findViewById(R.id.login_button_register);
        Intent intent_register= new Intent(login.this, com.example.kofico.activity.register.class);
        Intent intent_homepage = new Intent(login.this, com.example.kofico.activity.homepage.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   startActivity(intent_homepage);

            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent_register);

            }
        });

}
}