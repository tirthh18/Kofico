package com.example.kofico.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.kofico.R;
import com.example.kofico.databinding.ActivityMainBinding;
import com.example.kofico.fragment.cart;
import com.example.kofico.fragment.home;
import com.example.kofico.fragment.order;
import com.example.kofico.fragment.profile;

public class MainActivity extends AppCompatActivity {

    public static ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new home());
        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.homefragment) {
                replaceFragment(new home());
                return true;
            } else if (item.getItemId() == R.id.profilefragment) {
                replaceFragment(new profile());
                return true;
            } else if (item.getItemId() == R.id.orderfragment) {
                replaceFragment(new order());
                return true;
            } else if (item.getItemId() == R.id.cartfragment) {
                replaceFragment(new cart());
                return true;
            }
            return false;
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(binding.frameLayout.getId(), fragment);
        fragmentTransaction.commit();
    }
}
