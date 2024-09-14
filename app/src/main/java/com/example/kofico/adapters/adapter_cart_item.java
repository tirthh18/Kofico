package com.example.kofico.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.kofico.R;
import com.example.kofico.models.item_home;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class adapter_cart_item extends RecyclerView.Adapter<adapter_cart_item.ViewHolder> {
    private List<Map.Entry<item_home, Integer>> cartItemList;  // Use Map.Entry to get both the item and its quantity

    public adapter_cart_item(Map<item_home, Integer> cartItems) {
        this.cartItemList = new ArrayList<>(cartItems.entrySet());  // Convert map to list of entries
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

        // Set the quantity
        holder.itemQuantityTextView.setText(String.valueOf(quantity));
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView titleTextView;
        public TextView priceTextView;
        public TextView itemQuantityTextView;  // This TextView is for the quantity

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            titleTextView = itemView.findViewById(R.id.name);
            priceTextView = itemView.findViewById(R.id.price);
            itemQuantityTextView = itemView.findViewById(R.id.item_no);
        }
    }
}
