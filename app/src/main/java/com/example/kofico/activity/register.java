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
            EditText regName=findViewById(R.id.register_full_name);
            EditText regUsername=findViewById(R.id.register_username);
            EditText regEmail=findViewById(R.id.register_email);
            EditText regPassword=findViewById(R.id.register_password);
            Button signup=findViewById(R.id.register_button_register);
            adapter_dbhelper DB=new adapter_dbhelper(this);

            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name=regName.getText().toString();
                    String username=regUsername.getText().toString();
                    String email=regEmail.getText().toString();
                    String password=regPassword.getText().toString();
                    if(username.equals("")||name.equals("")||password.equals("")||email.equals("")||password.equals("")){
                        Toast.makeText(register.this,"please enter all the details",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Boolean checkuser=DB.checkusername(name,password);
                        if(checkuser==false){
                            Boolean insert=DB.insertData(username,name,password,email);
                            if(insert==true){
                                Toast.makeText(getApplicationContext(),"register successfully,please login now",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(register.this,login.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(register.this,"register failed",Toast.LENGTH_SHORT).show();

                            }
                        }
                        else{
                            Toast.makeText(register.this,"user already exist",Toast.LENGTH_SHORT).show();

                        }
                    }
                }
            });
        }

    }