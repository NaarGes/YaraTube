package com.example.asus.yaratube.ui.login;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.example.asus.yaratube.R;
import com.example.asus.yaratube.ui.login.LoginCode.LoginCodeFragment;
import com.example.asus.yaratube.ui.login.LoginMethod.LoginMethodFragment;
import com.example.asus.yaratube.ui.login.LoginPhone.LoginPhoneFragment;

public class LoginDialogFragment extends DialogFragment implements LoginDialogContract.steps {

    public static LoginDialogFragment newInstance() {

        Bundle args = new Bundle();

        LoginDialogFragment fragment = new LoginDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result =  inflater.inflate(R.layout.fragment_login_dialog, container, false);
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


    @Override
    public void goToLoginPhone() {

        LoginPhoneFragment loginPhoneFragment = LoginPhoneFragment.newInstance();
        loginPhoneFragment.setListener(this);
        getChildFragmentManager().beginTransaction().addToBackStack(loginPhoneFragment.getClass().getName())
                .replace(R.id.login_container, loginPhoneFragment).commit();
    }

    @Override
    public void goToLoginCode(String phoneNumber) {

        LoginCodeFragment loginCodeFragment = LoginCodeFragment.newInstance(phoneNumber);
        loginCodeFragment.setListener(this);
        getChildFragmentManager().beginTransaction().addToBackStack(loginCodeFragment.getClass().getName())
                .replace(R.id.login_container, loginCodeFragment).commit();
    }
}
