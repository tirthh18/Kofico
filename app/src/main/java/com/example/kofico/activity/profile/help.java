package com.example.kofico.activity.profile;

import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.kofico.R;

public class help extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(v -> finish());
    }
}
