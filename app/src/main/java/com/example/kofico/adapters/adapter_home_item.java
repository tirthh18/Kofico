package com.example.kofico.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.kofico.R;
import com.example.kofico.models.item_home;
import com.bumptech.glide.Glide;

import java.util.List;

public class adapter_home_item extends RecyclerView.Adapter<adapter_home_item.ViewHolder> {

    private List<item_home> coffeeItemList;
    private OnAddToCartClickListener onAddToCartClickListener;

    public interface OnAddToCartClickListener {
        void onAddToCart(item_home item);
    }

    public adapter_home_item(List<item_home> coffeeItemList,OnAddToCartClickListener listener) {
        this.coffeeItemList = coffeeItemList;
        this.onAddToCartClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.holder_home_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        item_home coffeeItem = coffeeItemList.get(position);

        // Load image using Glide
        Glide.with(holder.itemView.getContext())
                .load(coffeeItem.getImageResId())
                .override(215, 240) // Set fixed dimensions for images
                .centerCrop()        // Ensures proper scaling
                .placeholder(R.drawable.ic_cart) // Placeholder image
                .into(holder.imageView);

        holder.titleTextView.setText(coffeeItem.getTitle());
        holder.ratingTextView.setText(String.valueOf(coffeeItem.getRating()));
        holder.priceTextView.setText(coffeeItem.getPrice());

        holder.addtocart.setOnClickListener(v -> {
            if (onAddToCartClickListener != null) {
                // Show Toast message when item is added to cart
                Toast.makeText(holder.itemView.getContext(), coffeeItem.getTitle() + " added to cart", Toast.LENGTH_SHORT).show();

                // Trigger the listener callback
                onAddToCartClickListener.onAddToCart(coffeeItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return coffeeItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView titleTextView;
        public TextView ratingTextView;
        public TextView priceTextView;
        public Button addtocart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            titleTextView = itemView.findViewById(R.id.name);
            ratingTextView = itemView.findViewById(R.id.rating);
            priceTextView = itemView.findViewById(R.id.price);
            addtocart = itemView.findViewById(R.id.add_to_cart);
        }
    }
}
