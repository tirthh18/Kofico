// Generated by view binder compiler. Do not edit!
package com.example.kofico.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.kofico.R;
import com.makeramen.roundedimageview.RoundedImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class HolderHomeItemBinding implements ViewBinding {
  @NonNull
  private final CardView rootView;

  @NonNull
  public final AppCompatButton addToCart;

  @NonNull
  public final RoundedImageView image;

  @NonNull
  public final TextView name;

  @NonNull
  public final TextView price;

  @NonNull
  public final TextView rating;

  private HolderHomeItemBinding(@NonNull CardView rootView, @NonNull AppCompatButton addToCart,
      @NonNull RoundedImageView image, @NonNull TextView name, @NonNull TextView price,
      @NonNull TextView rating) {
    this.rootView = rootView;
    this.addToCart = addToCart;
    this.image = image;
    this.name = name;
    this.price = price;
    this.rating = rating;
  }

  @Override
  @NonNull
  public CardView getRoot() {
    return rootView;
  }

  @NonNull
  public static HolderHomeItemBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static HolderHomeItemBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.holder_home_item, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static HolderHomeItemBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.add_to_cart;
      AppCompatButton addToCart = ViewBindings.findChildViewById(rootView, id);
      if (addToCart == null) {
        break missingId;
      }

      id = R.id.image;
      RoundedImageView image = ViewBindings.findChildViewById(rootView, id);
      if (image == null) {
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

      id = R.id.rating;
      TextView rating = ViewBindings.findChildViewById(rootView, id);
      if (rating == null) {
        break missingId;
      }

      return new HolderHomeItemBinding((CardView) rootView, addToCart, image, name, price, rating);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}