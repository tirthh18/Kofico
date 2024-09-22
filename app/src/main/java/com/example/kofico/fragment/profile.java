package com.example.kofico.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.kofico.activity.login;
import com.example.kofico.activity.profile.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.kofico.R;
import com.example.kofico.adapters.adapter_dbhelper;
import com.example.kofico.models.user;

public class profile extends Fragment {
    private adapter_dbhelper dbHelper;
    SharedPreferences pref;
    private TextView profileName;
    private TextView profileEmail;
    private View help;
    private View security;
    private View logout;
    private user user;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new adapter_dbhelper(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        profileName = view.findViewById(R.id.profile_name);
        profileEmail = view.findViewById(R.id.profile_email);
        help = view.findViewById(R.id.help);
        security = view.findViewById(R.id.security);
        logout = view.findViewById(R.id.logout);
        return view;
//        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getUserInfo();
        help.setOnClickListener(v -> {
            startActivity( new Intent(getActivity(), com.example.kofico.activity.profile.help.class));
        });
        security.setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), sequrity.class));
        });
        logout.setOnClickListener(v -> {
            SharedPreferences.Editor edit =pref.edit();
            edit.clear();
            edit.commit();
            startActivity(new Intent(getActivity(), login.class));
        });

    }

    private void getUserInfo() {
        pref = getActivity().getSharedPreferences("user_details", MODE_PRIVATE);
        String uname = pref.getString("username", null);
        if (uname != null) {
            user = dbHelper.getUserDetails(uname);
            if (user != null) {
                profileName.setText(user.getName());
                profileEmail.setText(user.getEmail());
            } else {
                // Handle the case when user is null (e.g., show an error message)
                Toast.makeText(getActivity(), "User not found", Toast.LENGTH_SHORT).show();
            }
        } else {
            // Handle the case when username is null (e.g., show an error message)
            Toast.makeText(getActivity(), "No username found", Toast.LENGTH_SHORT).show();
        }
    }

}