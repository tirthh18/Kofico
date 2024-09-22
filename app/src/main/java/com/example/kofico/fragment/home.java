package com.example.kofico.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.kofico.R;
import com.example.kofico.adapters.adapter_dbhelper;
import com.example.kofico.adapters.adapter_home_item;
import com.example.kofico.models.ItemUtils;
import com.example.kofico.models.item_home;

import java.util.ArrayList;
import java.util.List;

public class home extends Fragment {
    private RecyclerView recyclerView;
    private adapter_home_item adapter;
    private List<item_home> coffeeItemList;
    private adapter_dbhelper dbhelper;
    int currentUserId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_all_item);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        dbhelper = new adapter_dbhelper(getContext());

        coffeeItemList = new ArrayList<>();
//        coffeeItemList.add(new item_home(1, R.drawable.ic_home, "Espresso", 4.5, "Rp 399"));
//        coffeeItemList.add(new item_home(2, R.drawable.ic_home, "Latte", 4.8, "Rp 599"));

        // In onViewCreated, replace the hardcoded items with utility method
        coffeeItemList.add(ItemUtils.fetchItemById(1)); // Espresso
        coffeeItemList.add(ItemUtils.fetchItemById(2)); // Latte
        String currentUsername = getCurrentUser();
        Log.d("CurrentUserCheck", "Fetched current username: " + currentUsername);

        adapter = new adapter_home_item(coffeeItemList, item -> {
            if (currentUsername != null) {
//                int currentUserId = dbhelper.getUserId(currentUsername);

                if (currentUserId != -1) {
                    boolean isInserted = dbhelper.insertCartData(currentUserId, item.getItemId(), 1);
                    if (isInserted) {
                        Toast.makeText(getContext(), item.getTitle() + " added to cart", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Failed to add to cart", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.d("UsernameCheck", "User ID not found for username: " + currentUsername);
                    Toast.makeText(getContext(), "User not found", Toast.LENGTH_SHORT).show();
                }
            } else {
                Log.d("UsernameCheck", "No username found.");
                Toast.makeText(getContext(), "No user logged in", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private String getCurrentUser() {
        SharedPreferences pref = getActivity().getSharedPreferences("user_details", MODE_PRIVATE);
        currentUserId=pref.getInt("userid", 1);
        return pref.getString("username", null);
    }
}
