package com.example.kofico.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.kofico.R;
import android.widget.EditText;
import android.widget.Toast;
import com.example.kofico.adapters.adapter_dbhelper;

public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize EditText fields and Button
        EditText regName = findViewById(R.id.register_full_name);
        EditText regUsername = findViewById(R.id.register_username);
        EditText regEmail = findViewById(R.id.register_email);
        EditText regPassword = findViewById(R.id.register_password);
        Button signup = findViewById(R.id.register_button_register);

        // Initialize Database Helper
        adapter_dbhelper DB = new adapter_dbhelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get user input from fields
                String name = regName.getText().toString();
                String username = regUsername.getText().toString();
                String email = regEmail.getText().toString();
                String password = regPassword.getText().toString();

                // Check if any field is empty
                if (username.equals("") || name.equals("") || password.equals("") || email.equals("")) {
                    Toast.makeText(register.this, "Please enter all the details", Toast.LENGTH_SHORT).show();
                } else {
                    // Check if the username already exists in the database
                    Boolean checkUser = DB.checkusername(username);
                    if (!checkUser) {
                        // If user doesn't exist, insert new user data
                        Boolean insert = DB.insertData(username, name, password, email);
                        if (insert) {
                            Toast.makeText(getApplicationContext(), "Registered successfully, please login now", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(register.this, login.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(register.this, "User already exists", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
