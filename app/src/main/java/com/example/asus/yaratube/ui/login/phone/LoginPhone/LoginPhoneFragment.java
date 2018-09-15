package com.example.asus.yaratube.ui.login.phone.LoginPhone;

import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.yaratube.R;
import com.example.asus.yaratube.ui.login.LoginDialogContract;
import com.example.asus.yaratube.util.Util;


public class LoginPhoneFragment extends Fragment implements LoginPhoneContract.View {

    private EditText phoneNumber;
    private LoginPhonePresenter presenter;
    private LoginDialogContract.steps listener;

    public LoginPhoneFragment() {

    }

    public void setListener(LoginDialogContract.steps listener) {
        this.listener = listener;
    }

    public static LoginPhoneFragment newInstance() {

        Bundle args = new Bundle();

        LoginPhoneFragment fragment = new LoginPhoneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoginPhonePresenter(this, getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_login_phone, container, false);

        return result;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        phoneNumber = view.findViewById(R.id.phone_number);
        Button submitPhone = view.findViewById(R.id.submit_phone_butt);

        submitPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                presenter.savePhoneNumber(phoneNumber.getText().toString());
                presenter.onSendPhoneNumber(Util.faToEn(phoneNumber.getText().toString()), Util.getDeviceId(view.getContext()),
                        Util.DEVICE_MODEL, Util.DEVICE_OS);
            }
        });
    }

    @Override
    public void showErrorMessage(String errorMessage) {

        Toast.makeText(this.getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void smsReceived(String phoneNumber) {

        listener.goToLoginCode(phoneNumber);
    }
}
