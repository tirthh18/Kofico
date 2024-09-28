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

import java.util.List;
import java.util.Map;

public class adapter_cart_item extends RecyclerView.Adapter<adapter_cart_item.CartViewHolder> {
    private List<Map.Entry<item_home, Integer>> cartItems;
    private adapter_dbhelper dbhelper;
    private int currentUserId;
    private Runnable refreshCallback;

    public adapter_cart_item(List<Map.Entry<item_home, Integer>> cartItems, adapter_dbhelper dbhelper,
                             int currentUserId, Runnable refreshCallback) {
        this.cartItems = cartItems;
        this.dbhelper = dbhelper;
        this.currentUserId = currentUserId;
        this.refreshCallback = refreshCallback;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.holder_cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Map.Entry<item_home, Integer> entry = cartItems.get(position);
        item_home item = entry.getKey();
        int quantity = entry.getValue();

        holder.titleTextView.setText(item.getTitle());
        holder.quantityTextView.setText(String.valueOf(quantity));
        holder.priceTextView.setText(item.getPrice());
        holder.imageView.setImageResource(item.getImageResId());

        holder.increaseButton.setOnClickListener(v -> {
            dbhelper.insertCartData(currentUserId, item.getItemId(), 1); // Update quantity in DB
            refreshCallback.run();
        });

        holder.decreaseButton.setOnClickListener(v -> {
            dbhelper.insertCartData(currentUserId, item.getItemId(), -1); // Update quantity in DB
            refreshCallback.run();
        });
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, quantityTextView, priceTextView;
        ImageView imageView;
        Button increaseButton, decreaseButton;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.name);
            quantityTextView = itemView.findViewById(R.id.quantity);
            priceTextView = itemView.findViewById(R.id.price);
            imageView = itemView.findViewById(R.id.image);
            increaseButton = itemView.findViewById(R.id.increase_button);
            decreaseButton = itemView.findViewById(R.id.decrease_button);
        }
    }
}
