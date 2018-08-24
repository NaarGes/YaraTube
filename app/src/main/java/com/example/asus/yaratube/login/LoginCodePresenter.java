package com.example.asus.yaratube.login;

import android.content.Context;
import android.util.Log;

import com.example.asus.yaratube.data.UserRepository;
import com.example.asus.yaratube.data.local.AppDatabase;
import com.example.asus.yaratube.data.local.UserEntity;
import com.example.asus.yaratube.data.model.Activation;
import com.example.asus.yaratube.data.remote.ApiResult;

public class LoginCodePresenter implements LoginCodeContract.Presenter {

    private LoginCodeContract.View view;
    private UserRepository repository;
    private AppDatabase database;
    private Context context;

    LoginCodePresenter(LoginCodeContract.View view, Context context, AppDatabase database) {

        this.view = view;
        this.context = context;
        repository = new UserRepository(context);
        this.database = database;
    }

    @Override
    public void onSendVerificationCode(String phoneNumber, String deviceId, int verificationCode) {

        repository.sendVerificationCode(new ApiResult<Activation>() {
            @Override
            public void onSuccess(Activation result) {


                Log.e("token fetched ", "onSuccess: "+result.getToken() );
                UserEntity user = new UserEntity();
                user.setToken(result.getToken());
                database.userDao().insert(user);
                Log.e("token saved ", "onSuccess: "+database.userDao().getToken() );
                view.activationDone();
            }

            @Override
            public void onFail(String errorMessage) {

                view.showErrorMessage(errorMessage);
            }
        }, phoneNumber, deviceId, verificationCode);
    }
}
