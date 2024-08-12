// Generated by view binder compiler. Do not edit!
package com.example.kofico.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.kofico.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityRegisterBinding implements ViewBinding {
  @NonNull
  private final RelativeLayout rootView;

  @NonNull
  public final ScrollView idScroll;

  @NonNull
  public final AppCompatButton registerButtonRegister;

  @NonNull
  public final EditText registerEmail;

  @NonNull
  public final EditText registerFullName;

  @NonNull
  public final LinearLayout registerLinear1;

  @NonNull
  public final EditText registerPassword;

  @NonNull
  public final TextView registerSubTitle;

  @NonNull
  public final TextView registerTitle;

  @NonNull
  public final EditText registerUsername;

  private ActivityRegisterBinding(@NonNull RelativeLayout rootView, @NonNull ScrollView idScroll,
      @NonNull AppCompatButton registerButtonRegister, @NonNull EditText registerEmail,
      @NonNull EditText registerFullName, @NonNull LinearLayout registerLinear1,
      @NonNull EditText registerPassword, @NonNull TextView registerSubTitle,
      @NonNull TextView registerTitle, @NonNull EditText registerUsername) {
    this.rootView = rootView;
    this.idScroll = idScroll;
    this.registerButtonRegister = registerButtonRegister;
    this.registerEmail = registerEmail;
    this.registerFullName = registerFullName;
    this.registerLinear1 = registerLinear1;
    this.registerPassword = registerPassword;
    this.registerSubTitle = registerSubTitle;
    this.registerTitle = registerTitle;
    this.registerUsername = registerUsername;
  }

  @Override
  @NonNull
  public RelativeLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityRegisterBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_register, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityRegisterBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.idScroll;
      ScrollView idScroll = ViewBindings.findChildViewById(rootView, id);
      if (idScroll == null) {
        break missingId;
      }

      id = R.id.register_button_register;
      AppCompatButton registerButtonRegister = ViewBindings.findChildViewById(rootView, id);
      if (registerButtonRegister == null) {
        break missingId;
      }

      id = R.id.register_email;
      EditText registerEmail = ViewBindings.findChildViewById(rootView, id);
      if (registerEmail == null) {
        break missingId;
      }

      id = R.id.register_full_name;
      EditText registerFullName = ViewBindings.findChildViewById(rootView, id);
      if (registerFullName == null) {
        break missingId;
      }

      id = R.id.register_linear1;
      LinearLayout registerLinear1 = ViewBindings.findChildViewById(rootView, id);
      if (registerLinear1 == null) {
        break missingId;
      }

      id = R.id.register_password;
      EditText registerPassword = ViewBindings.findChildViewById(rootView, id);
      if (registerPassword == null) {
        break missingId;
      }

      id = R.id.register_sub_title;
      TextView registerSubTitle = ViewBindings.findChildViewById(rootView, id);
      if (registerSubTitle == null) {
        break missingId;
      }

      id = R.id.register_title;
      TextView registerTitle = ViewBindings.findChildViewById(rootView, id);
      if (registerTitle == null) {
        break missingId;
      }

      id = R.id.register_username;
      EditText registerUsername = ViewBindings.findChildViewById(rootView, id);
      if (registerUsername == null) {
        break missingId;
      }

      return new ActivityRegisterBinding((RelativeLayout) rootView, idScroll,
          registerButtonRegister, registerEmail, registerFullName, registerLinear1,
          registerPassword, registerSubTitle, registerTitle, registerUsername);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}