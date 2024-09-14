package com.example.kofico.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.kofico.R;
import com.example.kofico.adapters.adapter_dbhelper;

public class login extends AppCompatActivity {
    Button  login_btn;
    EditText username, password;
    TextView register;
    SharedPreferences pref;
    Intent intent;
    String user,pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        register = findViewById(R.id.login_button_register);
        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        login_btn = findViewById(R.id.login_button_login);
        pref = getSharedPreferences("user_details",MODE_PRIVATE);
        intent = new Intent(login.this, MainActivity.class);
        adapter_dbhelper DB=new adapter_dbhelper(this);

        if(pref.contains("username") && pref.contains("password")){
            startActivity(intent);
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
            }});

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 user=username.getText().toString();
                 pass=password.getText().toString();
                if(user.equals("")||pass.equals("")){
                    Toast.makeText(login.this,"please enter all the details",Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkuserpass=DB.checkusername(user,pass);
                    if(checkuserpass==true){
                        saveprefrence();
                        Toast.makeText(login.this,"login successfull",Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(login.this,"not valid enter correct or sign up",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

    }
    void saveprefrence()
    {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("username",user);
        editor.putString("password",pass);
        editor.commit();
    }
}