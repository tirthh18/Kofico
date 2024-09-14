package com.example.kofico.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class cartviewmodel extends ViewModel {
    private final MutableLiveData<Map<item_home, Integer>> cartItems = new MutableLiveData<>(new HashMap<>());

    public LiveData<Map<item_home, Integer>> getCartItems() {
        return cartItems;
    }

    public void addItemToCart(item_home item) {
        Map<item_home, Integer> currentItems = cartItems.getValue();
        if (currentItems != null) {
            // Check if the item already exists in the cart
            if (currentItems.containsKey(item)) {
                // Increase the quantity of the existing item
                int currentQuantity = currentItems.get(item);
                currentItems.put(item, currentQuantity + 1);
            } else {
                // Add the new item with quantity 1
                currentItems.put(item, 1);
            }
            cartItems.setValue(currentItems);  // Update the LiveData with new cart items
        }
    }
    public int calculateSubtotal() {
        Map<item_home, Integer> items = cartItems.getValue(); // Get the map of items and their quantities
        int subtotal = 0;
        if (items != null) {
            for (Map.Entry<item_home, Integer> entry : items.entrySet()) {
                item_home item = entry.getKey();  // The item
                int quantity = entry.getValue();  // The quantity of that item

                try {
                    String priceString = item.getPrice().replace("Rp ", "").replace(",", "");
                    int price = Integer.parseInt(priceString);

                    // Add the total for this item (price * quantity) to the subtotal
                    subtotal += price * quantity;
                } catch (NumberFormatException e) {
                    e.printStackTrace();  // Handle any potential parsing error
                }
            }
        }
        return subtotal;
    }
}
