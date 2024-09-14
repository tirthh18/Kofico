package com.example.kofico.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.kofico.R;
import com.example.kofico.adapters.adapter_home_item;
import com.example.kofico.models.item_home;
import com.example.kofico.models.cartviewmodel;
import java.util.ArrayList;
import java.util.List;

public class home extends Fragment {
    private RecyclerView recyclerView;
    private adapter_home_item adapter;
    private List<item_home> coffeeItemList;
    private cartviewmodel cartViewModel;

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

        coffeeItemList = new ArrayList<>();
        coffeeItemList.add(new item_home(R.drawable.ic_home, "Espresso", 4.5, "Rp 399"));
        coffeeItemList.add(new item_home(R.drawable.ic_home, "Latte", 4.8, "Rp 599"));
        coffeeItemList.add(new item_home(R.drawable.ic_home, "Hazelnut Mocha", 4.8, "Rp 599"));
        coffeeItemList.add(new item_home(R.drawable.ic_home, "Caffé Mocha", 4.8, "Rp 399"));

        // Get CartViewModel
        cartViewModel = new ViewModelProvider(requireActivity()).get(cartviewmodel.class);

        // Adapter with listener to add items to cart
        adapter = new adapter_home_item(coffeeItemList, item -> {
            // Add the selected item to the cart in the ViewModel
            cartViewModel.addItemToCart(item);
        });

        recyclerView.setAdapter(adapter);
    }
}

