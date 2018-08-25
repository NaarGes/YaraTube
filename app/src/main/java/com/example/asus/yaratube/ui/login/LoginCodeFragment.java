package com.example.asus.yaratube.ui.login;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.yaratube.R;
import com.example.asus.yaratube.ui.base.TransferBetweenFragments;
import com.example.asus.yaratube.data.local.AppDatabase;
import com.example.asus.yaratube.util.Util;

public class LoginCodeFragment extends DialogFragment implements LoginCodeContract.View {

    private String phoneNumber;
    private TransferBetweenFragments transferBetweenFragments;
    private LoginCodeContract.Presenter presenter;
    private static String PHONE_NUMBER = "phone number";

    public LoginCodeFragment() {

    }

    public static LoginCodeFragment newInstance(String phoneNumber) {

        Bundle args = new Bundle();

        LoginCodeFragment fragment = new LoginCodeFragment();
        args.putString(PHONE_NUMBER, phoneNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            this.phoneNumber = getArguments().getString(PHONE_NUMBER);
        }
        final AppDatabase database = AppDatabase.getAppDatabase(getActivity());
        presenter = new LoginCodePresenter(this, getContext(), database);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        transferBetweenFragments = (TransferBetweenFragments) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        transferBetweenFragments = null;
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

        submitCodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("request params ", "onClick: "+phoneNumber+" "+deviceId+" "+verificationCode.getText().toString() );
                presenter.onSendVerificationCode(phoneNumber, deviceId, Integer.parseInt(verificationCode.getText().toString()));
                Util.hideKeyboardFrom(getContext(), view);
            }
        });

        editPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transferBetweenFragments.goToLoginPhone();
            }
        });
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);

        // request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void activationDone() {

        Toast.makeText(this.getContext(), "ورود شما با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showErrorMessage(String errorMessage) {

        hideProgressBar();
        Toast.makeText(this.getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }
}
