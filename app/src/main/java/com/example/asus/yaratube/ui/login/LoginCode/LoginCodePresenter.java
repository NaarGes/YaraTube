package com.example.asus.yaratube.ui.login.LoginCode;

import android.content.Context;

import com.example.asus.yaratube.data.local.LocalRepository;
import com.example.asus.yaratube.data.remote.UserRepository;
import com.example.asus.yaratube.data.local.AppDatabase;
import com.example.asus.yaratube.data.local.UserEntity;
import com.example.asus.yaratube.data.model.Activation;
import com.example.asus.yaratube.data.remote.ApiResult;

public class LoginCodePresenter implements LoginCodeContract.Presenter {

    private LoginCodeContract.View view;
    private UserRepository repository;
    private AppDatabase database;
    private Context context;
    private LocalRepository localRepository;

    LoginCodePresenter(LoginCodeContract.View view, Context context, AppDatabase database) {

        this.view = view;
        this.context = context;
        repository = new UserRepository(context);
        this.database = database;
        localRepository = new LocalRepository(database);
    }

    @Override
    public void onSendVerificationCode(String phoneNumber, String deviceId, int verificationCode) {

        repository.sendVerificationCode(new ApiResult<Activation>() {
            @Override
            public void onSuccess(Activation result) {

                // FIXME null pointer in context
                //view.activationDone();
                UserEntity user = new UserEntity();
                user.setToken(result.getToken());
                localRepository.loginUser(user);
            }

            @Override
            public void onFail(String errorMessage) {

                view.showErrorMessage(errorMessage);
            }
        }, phoneNumber, deviceId, verificationCode);
    }
}
