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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.yaratube.R;
import com.example.asus.yaratube.data.local.AppDatabase;
import com.example.asus.yaratube.ui.base.MainActivity;
import com.example.asus.yaratube.ui.login.LoginCode.LoginCodeFragment;
import com.example.asus.yaratube.ui.login.LoginMethod.LoginMethodFragment;
import com.example.asus.yaratube.ui.login.LoginPhone.LoginPhoneFragment;
import com.example.asus.yaratube.util.Util;

public class LoginDialogFragment extends DialogFragment implements LoginDialogContract.steps, LoginDialogContract.View {

    private LoginDialogPresenter presenter;

    public static LoginDialogFragment newInstance() {

        Bundle args = new Bundle();

        LoginDialogFragment fragment = new LoginDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoginDialogPresenter(this, getContext());
    }

    @SuppressLint("CommitPrefEdits")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View result =  inflater.inflate(R.layout.fragment_login_dialog, container, false);

        int loginStep = Util.getLoginStep((MainActivity) getActivity());
        if(loginStep == 2)
            goToLoginPhone();
        else if(loginStep == 3)
            goToLoginCode(presenter.phoneNumber());
        else
            goToLoginMethod();
        return result;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button close = view.findViewById(R.id.close_butt);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
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
        getChildFragmentManager().beginTransaction().replace(R.id.login_container, loginMethodFragment).commit();
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    public void goToLoginPhone() {

        LoginPhoneFragment loginPhoneFragment = LoginPhoneFragment.newInstance();
        loginPhoneFragment.setListener(this);
        getChildFragmentManager().beginTransaction().replace(R.id.login_container, loginPhoneFragment).commit();
    }

    @SuppressLint("CommitPrefEdits")
    @Override
    public void goToLoginCode(String phoneNumber) {

        Util.setLoginStep((MainActivity) getActivity(), 3);
        LoginCodeFragment loginCodeFragment = LoginCodeFragment.newInstance();
        loginCodeFragment.setListener(this);
        getChildFragmentManager().beginTransaction().replace(R.id.login_container, loginCodeFragment).commit();
    }
}
