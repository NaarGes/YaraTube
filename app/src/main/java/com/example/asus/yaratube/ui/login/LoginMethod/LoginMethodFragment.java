package com.example.asus.yaratube.ui.login.LoginMethod;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.asus.yaratube.R;
import com.example.asus.yaratube.ui.login.LoginDialogContract;
import com.example.asus.yaratube.util.Util;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class LoginMethodFragment extends Fragment implements LoginMethodContract.View,
                                                        GoogleApiClient.OnConnectionFailedListener {

    private LoginDialogContract.steps listener;
    private GoogleApiClient googleApiClient;
    private static final int REQUEST_CODE = 9001;
    private LoginMethodPresenter presenter;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new LoginMethodPresenter(this, getContext());

        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(getContext()).enableAutoManage(getActivity(), this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, signInOptions).build();

    }

    @Override
    public void onPause() {
        super.onPause();

        googleApiClient.stopAutoManage(getActivity());
        googleApiClient.disconnect();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_method, container, false);
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

        Button googleLogin = view.findViewById(R.id.google_method_butt);
        googleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                toast("لطفا کمی صبر کنید...");
                signIn();
            }
        });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        // todo
    }

    private void signIn() {

        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, REQUEST_CODE);
    }

    private void signOut() {

        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                // TODO
            }
        });
    }

    private void handleResult(GoogleSignInResult result) {

        // success login
        Log.e("result is success", "handleResult: "+result.isSuccess() );
        if(result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            String name = account.getDisplayName();
            String email = account.getEmail();
            String photoUrl = account.getPhotoUrl().toString();
            Log.e("profile info", "handleResult: "+name+" "+email+" "+photoUrl+ " "+account.getIdToken() );

            presenter.onSendGoogleToken(account.getIdToken(), Util.getDeviceId(getContext()),
                    Util.DEVICE_OS, Util.DEVICE_MODEL, name, email, photoUrl);
        } else { // login fail
            toast("لطفا مجددا تلاش کنید");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            int statusCode = result.getStatus().getStatusCode();
            Log.e("result status code", "onActivityResult: "+statusCode);
            Log.e("result", "onActivityResult: "+result);
            handleResult(result);
        }
    }

    @Override
    public void dismissDialog() {
        assert getParentFragment() != null;
        ((DialogFragment) getParentFragment()).dismiss();
    }

    @Override
    public void toast(String message) {
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
