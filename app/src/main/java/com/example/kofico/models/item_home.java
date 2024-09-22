package com.example.kofico.models;

import androidx.annotation.NonNull;

public class item_home {
    private int itemId;
    private int imageResId;
    private String title;
    private double rating;
    private String price;

    public item_home(int itemId, int imageResId, String title, double rating, String price) {
        this.itemId = itemId;
        this.imageResId = imageResId;
        this.title = title;
        this.rating = rating;
        this.price = price;
    }

    public int getItemId() {
        return itemId;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }

    public double getRating() {
        return rating;
    }

    public String getPrice() {
        return price;
    }
}
