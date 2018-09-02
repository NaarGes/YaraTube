package com.example.asus.yaratube.ui.login.LoginMethod;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.asus.yaratube.R;
import com.example.asus.yaratube.ui.login.LoginDialogContract;

public class LoginMethodFragment extends Fragment  {

    private LoginDialogContract.steps listener;

    public LoginMethodFragment() {

    }

    public void setListener(LoginDialogContract.steps listener) {
        this.listener = listener;
    }

    public static LoginMethodFragment newInstance() {

        Bundle args = new Bundle();
        LoginMethodFragment fragment = new LoginMethodFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_login_method, container, false);

        return result;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button phoneLogin = view.findViewById(R.id.phone_method_butt);
        phoneLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.goToLoginPhone();
            }
        });
    }
}
