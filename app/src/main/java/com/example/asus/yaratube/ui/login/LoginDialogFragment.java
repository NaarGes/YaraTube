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
import com.example.asus.yaratube.data.local.AppDatabase;
import com.example.asus.yaratube.ui.login.LoginCode.LoginCodeFragment;
import com.example.asus.yaratube.ui.login.LoginMethod.LoginMethodFragment;
import com.example.asus.yaratube.ui.login.LoginPhone.LoginPhoneFragment;

public class LoginDialogFragment extends DialogFragment implements LoginDialogContract.steps {

    private SharedPreferences.Editor editor;

    private AppDatabase database;

    public static LoginDialogFragment newInstance() {

        Bundle args = new Bundle();

        LoginDialogFragment fragment = new LoginDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = AppDatabase.getAppDatabase(getActivity());
    }

    @SuppressLint("CommitPrefEdits")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result =  inflater.inflate(R.layout.fragment_login_dialog, container, false);
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        int loginStep = sharedPreferences.getInt("Login Step", 1);
        if(loginStep == 2)
            goToLoginPhone();
        else if(loginStep == 3)
            goToLoginCode(database.userDao().getPhoneNumber());
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

        editor.clear();
        editor.putInt("Login Step", 3);
        editor.commit();
        LoginCodeFragment loginCodeFragment = LoginCodeFragment.newInstance();
        loginCodeFragment.setListener(this);
        getChildFragmentManager().beginTransaction().replace(R.id.login_container, loginCodeFragment).commit();
    }
}
