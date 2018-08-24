package com.example.asus.yaratube.login;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.yaratube.R;

public class LoginPhoneFragment extends DialogFragment implements LoginContract.View {

    private EditText phoneNumber;
    private Button submitPhone;
    private LoginPhonePresenter presenter;

    public LoginPhoneFragment() {

    }

    public static LoginPhoneFragment newInstance() {

        Bundle args = new Bundle();

        LoginPhoneFragment fragment = new LoginPhoneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
        submitPhone = view.findViewById(R.id.submit_phone_butt);

        final String deviceId = Settings.Secure.getString(view.getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        final String deviceModel = Build.MODEL;
        final String deviceOs = "Android " + Build.VERSION.SDK_INT;

        submitPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("chizzzz", "onClick: "+deviceId+" "+deviceModel+" "+deviceOs+" "+phoneNumber.getText().toString() );
                presenter.onSendPhoneNumber(phoneNumber.getText().toString(), deviceId, deviceModel, deviceOs);
            }
        });
    }


    @Override
    public void showResponseMessage(String message) {

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
