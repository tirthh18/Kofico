package com.example.kofico.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.kofico.adapters.adapter_cart_item;
import com.example.kofico.R;
import com.example.kofico.models.cartviewmodel;

public class cart extends Fragment {
    private cartviewmodel cartViewModel;
    private RecyclerView recyclerView;
    private adapter_cart_item adapter;
    private TextView subtotalTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_carts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        subtotalTextView = view.findViewById(R.id.tv_subtotal);

        cartViewModel = new ViewModelProvider(requireActivity()).get(cartviewmodel.class);

        // Observe the LiveData for changes to the cart items
        cartViewModel.getCartItems().observe(getViewLifecycleOwner(), items -> {
            // Update the RecyclerView adapter
            adapter = new adapter_cart_item(items);
            recyclerView.setAdapter(adapter);

            // Calculate and update the subtotal
            int subtotal = cartViewModel.calculateSubtotal();
            subtotalTextView.setText("Rp " + subtotal);
        });
    }
}
