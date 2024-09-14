package com.example.kofico.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class item_home {
    private int imageResId;  // Drawable resource ID
    private String title;
    private double rating;
    private String price;

    // Constructor
    public item_home(int imageResId, String title, double rating, String price) {
        this.imageResId = imageResId;
        this.title = title;
        this.rating = rating;
        this.price = price;
    }

    // Getters
    public int getImageResId() { return imageResId; }
    public String getTitle() { return title; }
    public double getRating() { return rating; }
    public String getPrice() { return price; }

    // Override equals() method to compare items by title and price
    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        item_home item = (item_home) obj;
        // Compare title and price to determine if the items are the same
        return title.equals(item.title) && price.equals(item.price);
    }

    // Override hashCode() method to ensure consistent behavior in HashMap
    @Override
    public int hashCode() {
        // Generate hashCode based on title and price
        return title.hashCode() + price.hashCode();
    }

    @NonNull
    @Override
    public String toString() {
        return "Item: " + title + " Price: " + price;
    }
}
