package com.example.kofico.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kofico.R;
import com.example.kofico.activity.MainActivity;
import com.example.kofico.adapters.adapter_cart_item;
import com.example.kofico.adapters.adapter_checkout_item;
import com.example.kofico.adapters.adapter_dbhelper;
import com.example.kofico.models.item_home;

import java.util.List;
import java.util.Map;

public class checkout extends Fragment {
    private RecyclerView recyclerView;
    private adapter_checkout_item adapter;
    private TextView totalTextView;
    private adapter_dbhelper dbhelper;
    private int currentUserId;
    private Button make_an_order ;
    private ImageView imageView;
    String total;

    public static checkout newInstance(String total) {
        checkout fragment = new checkout();
        Bundle args = new Bundle();
        args.putString("total", total);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_checkout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_checkout);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        totalTextView = view.findViewById(R.id.tv_price);
        make_an_order = view.findViewById(R.id.order);
        dbhelper = new adapter_dbhelper(getContext());
        imageView = view.findViewById(R.id.back);

        if (getArguments() != null) {
            total = getArguments().getString("total");
            totalTextView.setText(total);
        }

        currentUserId = getCurrentUserId();
        refreshCartItems();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment cart = new cart();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, cart);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        make_an_order.setOnClickListener(v -> {
            try {
                boolean orderSuccess = dbhelper.insertOrder(currentUserId,total);
                if (orderSuccess) {
                    // Clear the cart after successfully placing an order
                    dbhelper.clearCartForUser(currentUserId);

                    Toast.makeText(getContext(), "Order placed successfully!", Toast.LENGTH_SHORT).show();
                    Fragment order = new order();
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_layout, order);
                    transaction.addToBackStack(null);
                    transaction.commit();


                    MainActivity.binding.bottomNavigationView.getMenu().getItem(2).setChecked(true);



                } else {
                    Toast.makeText(getContext(), "No items in the cart to place an order.", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(getContext(), "Invalid user ID format.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private int getCurrentUserId() {
        SharedPreferences pref = getActivity().getSharedPreferences("user_details", MODE_PRIVATE);
        return pref.getInt("userid", -1); // Change this to -1 or a default value
    }
    private void refreshCartItems() {
        List<Map.Entry<item_home, Integer>> sortedCartItems = dbhelper.getSortedCartItems(currentUserId);
        adapter = new adapter_checkout_item(sortedCartItems, dbhelper, String.valueOf(currentUserId), this::refreshCartItems);
        recyclerView.setAdapter(adapter);
    }
}
