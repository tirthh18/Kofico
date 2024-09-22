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
    Button login_btn;
    EditText username, password;
    TextView register;
    SharedPreferences pref;
    Intent intent;
    String user, pass;
    private adapter_dbhelper dbhelper; // Declare dbhelper


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        register = findViewById(R.id.tv_login_button_register);
        username = findViewById(R.id.et_login_username);
        password = findViewById(R.id.et_login_password);
        login_btn = findViewById(R.id.login_button_login);
        pref = getSharedPreferences("user_details", MODE_PRIVATE);
        intent = new Intent(login.this, MainActivity.class);
        dbhelper = new adapter_dbhelper(this); // Initialize dbhelper

        // Auto-login if user is already saved
        if (pref.contains("username") && pref.contains("password")) {
            startActivity(intent);
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this, register.class);
                startActivity(intent);
            }
        });

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = username.getText().toString();
                pass = password.getText().toString();

                if (user.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(login.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkuserpass = dbhelper.checkUserLogin(user, pass);  // Use dbhelper instance

                    if (checkuserpass) {
                        savePreference(); // Save user details
                        Toast.makeText(login.this, "Login successful", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    } else {
                        Toast.makeText(login.this, "Invalid credentials, please try again or sign up", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    void savePreference() {
        int currentUserId = dbhelper.getUserId(user); // Get user ID from dbhelper
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("userid", currentUserId); // Save user ID
        editor.putString("username", user); // Save username
        editor.putString("password", pass); // Save password
        editor.apply(); // Use apply for asynchronous saving
    }
}
