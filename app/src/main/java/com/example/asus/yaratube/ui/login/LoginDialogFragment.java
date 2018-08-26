package com.example.asus.yaratube.ui.login;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.asus.yaratube.R;
import com.example.asus.yaratube.ui.base.MainActivity;
import com.example.asus.yaratube.ui.login.LoginCode.LoginCodeFragment;
import com.example.asus.yaratube.ui.login.LoginMethod.LoginMethodFragment;
import com.example.asus.yaratube.ui.login.LoginPhone.LoginPhoneFragment;

public class LoginDialogFragment extends DialogFragment implements LoginDialogContract.steps {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int loginStep;

    public static LoginDialogFragment newInstance() {

        Bundle args = new Bundle();

        LoginDialogFragment fragment = new LoginDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("CommitPrefEdits")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result =  inflater.inflate(R.layout.fragment_login_dialog, container, false);
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        loginStep = sharedPreferences.getInt("Login Step", 1);
        // = 1: select login method
        // = 2: login mobile, enter phone number
        // = 3: login mobile, enter verification code
        // = 4: login google ...
        if(loginStep == 2)
            goToLoginPhone();
        else if(loginStep == 3)
            goToLoginCode(""); // FIXME here we need to read phone number from database
        else
            goToLoginMethod();
        return result;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    public void goToLoginMethod() {

        LoginMethodFragment loginMethodFragment = LoginMethodFragment.newInstance();
        loginMethodFragment.setListener(this);
        getChildFragmentManager().beginTransaction().addToBackStack(loginMethodFragment.getClass().getName())
                .replace(R.id.login_container, loginMethodFragment).commit();
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    public void goToLoginPhone() {

        editor.clear();
        editor.putInt("Login Step", 2);
        editor.commit();
        LoginPhoneFragment loginPhoneFragment = LoginPhoneFragment.newInstance();
        loginPhoneFragment.setListener(this);
        getChildFragmentManager().beginTransaction().addToBackStack(loginPhoneFragment.getClass().getName())
                .replace(R.id.login_container, loginPhoneFragment).commit();
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    public void goToLoginCode(String phoneNumber) {

        editor.clear();
        editor.putInt("Login Step", 3);
        editor.commit();
        LoginCodeFragment loginCodeFragment = LoginCodeFragment.newInstance(phoneNumber);
        loginCodeFragment.setListener(this);
        getChildFragmentManager().beginTransaction().addToBackStack(loginCodeFragment.getClass().getName())
                .replace(R.id.login_container, loginCodeFragment).commit();
    }
}
