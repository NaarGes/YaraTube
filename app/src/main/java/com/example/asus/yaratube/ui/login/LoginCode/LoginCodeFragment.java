package com.example.asus.yaratube.ui.login.LoginCode;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.asus.yaratube.R;
import com.example.asus.yaratube.data.local.AppDatabase;
import com.example.asus.yaratube.ui.login.LoginDialogContract;
import com.example.asus.yaratube.util.Util;

public class LoginCodeFragment extends Fragment implements LoginCodeContract.View {

    private String phoneNumber;
    private LoginCodeContract.Presenter presenter;
    private LoginDialogContract.steps listener;
    private static String PHONE_NUMBER = "phone number";

    public LoginCodeFragment() {

    }

    public void setListener(LoginDialogContract.steps listener) {
        this.listener = listener;
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

                if(!verificationCode.getText().toString().equals("")) {
                    presenter.onSendVerificationCode(phoneNumber, deviceId, Integer.parseInt(Util.faToEn(verificationCode.getText().toString())));
                    Util.hideKeyboardFrom(view.getContext(), view);
                    assert getParentFragment() != null;
                    ((DialogFragment) getParentFragment()).dismiss();
                }
                else
                    showErrorMessage("لطفا کد فعال سازی را وارد کنید");
            }
        });

        editPhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.goToLoginPhone();
                SharedPreferences sharedPreferences = (getActivity()).getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.putInt("Login Step", 2);
                editor.commit();
            }
        });
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
