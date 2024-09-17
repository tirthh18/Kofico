package com.example.kofico.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.kofico.R;
import com.example.kofico.models.item_home;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class adapter_cart_item extends RecyclerView.Adapter<adapter_cart_item.ViewHolder> {
    private List<Map.Entry<item_home, Integer>> cartItemList;

    public adapter_cart_item(Map<item_home, Integer> cartItems) {
        this.cartItemList = new ArrayList<>(cartItems.entrySet());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Map.Entry<item_home, Integer> entry = cartItemList.get(position);
        item_home currentItem = entry.getKey();
        int quantity = entry.getValue();

        // Set item data
        holder.titleTextView.setText(currentItem.getTitle());
        holder.priceTextView.setText(currentItem.getPrice());
        holder.imageView.setImageResource(currentItem.getImageResId());

        // Set the initial quantity
        holder.itemQuantityTextView.setText(String.valueOf(quantity));

        // Increase button logic
        holder.increaseButton.setOnClickListener(v -> {
            int currentQuantity = Integer.parseInt(holder.itemQuantityTextView.getText().toString());
            currentQuantity++;
            holder.itemQuantityTextView.setText(String.valueOf(currentQuantity));
            cartItemList.set(position, new AbstractMap.SimpleEntry<>(currentItem, currentQuantity));
        });

        // Decrease button logic
        holder.decreaseButton.setOnClickListener(v -> {
            int currentQuantity = Integer.parseInt(holder.itemQuantityTextView.getText().toString());
            if (currentQuantity > 1) {
                currentQuantity--;
                holder.itemQuantityTextView.setText(String.valueOf(currentQuantity));
                cartItemList.set(position, new AbstractMap.SimpleEntry<>(currentItem, currentQuantity));
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView titleTextView;
        public TextView priceTextView;
        public TextView itemQuantityTextView;
        public Button decreaseButton;
        public Button increaseButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            titleTextView = itemView.findViewById(R.id.name);
            priceTextView = itemView.findViewById(R.id.price);
            itemQuantityTextView = itemView.findViewById(R.id.item_no);
            decreaseButton = itemView.findViewById(R.id.decrease_button);
            increaseButton = itemView.findViewById(R.id.increase_button);
        }
    }
}

