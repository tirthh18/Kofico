// Generated by view binder compiler. Do not edit!
package com.example.kofico.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.kofico.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentCartBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final Button checkout;

  @NonNull
  public final RecyclerView rvCarts;

  @NonNull
  public final TextView tvPrice;

  @NonNull
  public final TextView tvSubtotal;

  @NonNull
  public final TextView tvTitle;

  private FragmentCartBinding(@NonNull RelativeLayout rootView, @NonNull Button checkout,
      @NonNull RecyclerView rvCarts, @NonNull TextView tvPrice, @NonNull TextView tvSubtotal,
      @NonNull TextView tvTitle) {
    this.rootView = rootView;
    this.checkout = checkout;
    this.rvCarts = rvCarts;
    this.tvPrice = tvPrice;
    this.tvSubtotal = tvSubtotal;
    this.tvTitle = tvTitle;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentCartBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentCartBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_cart, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentCartBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.checkout;
      Button checkout = ViewBindings.findChildViewById(rootView, id);
      if (checkout == null) {
        break missingId;
      }

      id = R.id.rv_carts;
      RecyclerView rvCarts = ViewBindings.findChildViewById(rootView, id);
      if (rvCarts == null) {
        break missingId;
      }

      id = R.id.tv_price;
      TextView tvPrice = ViewBindings.findChildViewById(rootView, id);
      if (tvPrice == null) {
        break missingId;
      }

      id = R.id.tv_subtotal;
      TextView tvSubtotal = ViewBindings.findChildViewById(rootView, id);
      if (tvSubtotal == null) {
        break missingId;
      }

      id = R.id.tv_title;
      TextView tvTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvTitle == null) {
        break missingId;
      }

      return new FragmentCartBinding((RelativeLayout) rootView, checkout, rvCarts, tvPrice,
          tvSubtotal, tvTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
