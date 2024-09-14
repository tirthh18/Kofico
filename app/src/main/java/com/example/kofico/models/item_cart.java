package com.example.kofico.models;

public class item_cart {
    private String title;
    private int price;
    private int imageResId;

    public item_cart(String title, int price, int imageResId) {
        this.title = title;
        this.price = price;
        this.imageResId = imageResId;
    }

    public String getTitle() {
        return title;
    }

    public int getPrice() {
        return price;
    }

    public int getImageResId() {
        return imageResId;
    }
}
