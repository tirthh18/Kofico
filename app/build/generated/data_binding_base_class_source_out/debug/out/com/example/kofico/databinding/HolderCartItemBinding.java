// Generated by view binder compiler. Do not edit!
package com.example.kofico.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.kofico.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class HolderCartItemBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final Button decreaseButton;

  @NonNull
  public final ImageView image;

  @NonNull
  public final Button increaseButton;

  @NonNull
  public final TextView name;

  @NonNull
  public final TextView price;

  @NonNull
  public final TextView quantity;

  private HolderCartItemBinding(@NonNull CardView rootView, @NonNull Button decreaseButton,
      @NonNull ImageView image, @NonNull Button increaseButton, @NonNull TextView name,
      @NonNull TextView price, @NonNull TextView quantity) {
    this.rootView = rootView;
    this.decreaseButton = decreaseButton;
    this.image = image;
    this.increaseButton = increaseButton;
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static HolderCartItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static HolderCartItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.holder_cart_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static HolderCartItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.decrease_button;
      Button decreaseButton = ViewBindings.findChildViewById(rootView, id);
      if (decreaseButton == null) {
        break missingId;
      }

      id = R.id.image;
      ImageView image = ViewBindings.findChildViewById(rootView, id);
      if (image == null) {
        break missingId;
      }

      id = R.id.increase_button;
      Button increaseButton = ViewBindings.findChildViewById(rootView, id);
      if (increaseButton == null) {
        break missingId;
      }

      id = R.id.name;
      TextView name = ViewBindings.findChildViewById(rootView, id);
      if (name == null) {
        break missingId;
      }

      id = R.id.price;
      TextView price = ViewBindings.findChildViewById(rootView, id);
      if (price == null) {
        break missingId;
      }

      id = R.id.quantity;
      TextView quantity = ViewBindings.findChildViewById(rootView, id);
      if (quantity == null) {
        break missingId;
      }

      return new HolderCartItemBinding((CardView) rootView, decreaseButton, image, increaseButton,
          name, price, quantity);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
