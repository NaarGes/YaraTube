package com.example.asus.yaratube.ui.login.LoginCode;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.yaratube.R;
import com.example.asus.yaratube.ui.base.MainActivity;
import com.example.asus.yaratube.ui.login.LoginDialogContract;
import com.example.asus.yaratube.util.Util;

public class LoginCodeFragment extends Fragment implements LoginCodeContract.View {

    private LoginCodeContract.Presenter presenter;
    private LoginDialogContract.steps listener;
    final int REQUEST_CODE_READ_SMS = 100;

    public LoginCodeFragment() {

    }

    public void setListener(LoginDialogContract.steps listener) {
        this.listener = listener;
    }

    public static LoginCodeFragment newInstance() {

        Bundle args = new Bundle();
        LoginCodeFragment fragment = new LoginCodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new LoginCodePresenter(this, getContext());
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.RECEIVE_SMS}, REQUEST_CODE_READ_SMS);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SMSReceiver.unbindListener();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_login_code, container, false);
        return result;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button submitCodeButton = view.findViewById(R.id.submit_code_butt);
        Button editPhoneButton = view.findViewById(R.id.edit_phone_butt);

        final EditText verificationCode = view.findViewById(R.id.activation_code);

        final String deviceId = Settings.Secure.getString(view.getContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        SMSReceiver.bindListener(new LoginCodeContract.OTPListener() {
            @Override
            public void onOTPReceived(String otp) {
                verificationCode.setText(otp);
            }
        });

        submitCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Util.validateActivationCode(verificationCode.getText().toString())) {
                    // fixme send user instead of fields
                    presenter.onSendVerificationCode(presenter.phoneNumber(), deviceId,
                            Integer.parseInt(Util.faToEn(verificationCode.getText().toString())));
                    Util.hideKeyboardFrom(view.getContext(), view);
                }
                else
                    showErrorMessage("کد نامعتبر است");
            }
        });

        editPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.goToLoginPhone();
                Util.setLoginStep((MainActivity) getActivity(), 1);
            }
        });
    }

    @Override
    public void activationDone() {

        Toast.makeText(this.getContext(), "ورود شما با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissDialog() {
        assert getParentFragment() != null;
        ((DialogFragment) getParentFragment()).dismiss();
    }

    @Override
    public void showErrorMessage(String errorMessage) {

        Toast.makeText(this.getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE_READ_SMS:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)

                    showErrorMessage("Permission Granted");
                else
                    showErrorMessage("Permission Denied");
        }
    }
}
