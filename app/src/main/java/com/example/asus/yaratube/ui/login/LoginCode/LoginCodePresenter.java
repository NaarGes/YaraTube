package com.example.asus.yaratube.ui.login.LoginCode;

import android.content.Context;

import com.example.asus.yaratube.data.UserRepository;
import com.example.asus.yaratube.data.local.AppDatabase;
import com.example.asus.yaratube.data.local.UserEntity;
import com.example.asus.yaratube.data.model.Activation;
import com.example.asus.yaratube.data.remote.ApiResult;

public class LoginCodePresenter implements LoginCodeContract.Presenter {

    private LoginCodeContract.View view;
    private Context context;
    private UserRepository repository;

    LoginCodePresenter(LoginCodeContract.View view, Context context, AppDatabase database) {

        this.view = view;
        this.context = context;
        repository = new UserRepository(context);
        repository.setDatabase(database);
    }

    @Override
    public void onSendVerificationCode(String phoneNumber, String deviceId, int verificationCode) {

        repository.sendVerificationCode(phoneNumber, deviceId, verificationCode,
                new ApiResult<Activation>() {
            @Override
            public void onSuccess(Activation result) {

                view.activationDone();
                UserEntity userEntity = repository.getUser();
                userEntity.setToken(result.getToken());
                userEntity.setPhoneNumber(repository.phoneNumber());
                repository.updateUser(userEntity);
                view.dismissDialog();
            }

            @Override
            public void onFail(String errorMessage) {

                view.showErrorMessage(errorMessage);
            }
        });
    }

    @Override
    public String phoneNumber() {
        return repository.phoneNumber();
    }
}
