package com.example.kofico.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kofico.R;
import com.example.kofico.adapters.adapter_dbhendler;

public class login extends AppCompatActivity {
    Button  login_btn;
    EditText username, password;
    TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        register = findViewById(R.id.login_button_register);
        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        login_btn = findViewById(R.id.login_button_login);
        adapter_dbhendler DB=new adapter_dbhendler(this);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
            }});
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                if(user.equals("")||pass.equals("")){
                    Toast.makeText(login.this,"please enter all the details",Toast.LENGTH_SHORT).show();

                }
                else{
                    Boolean checkuserpass=DB.checkusername(user,pass);
                    if(checkuserpass==true){
                        Toast.makeText(login.this,"login successfull",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(login.this,homepage.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(login.this,"not valid enter correct or sign up",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

    }
}