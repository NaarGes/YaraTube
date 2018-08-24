package com.example.asus.yaratube.login;

import android.content.Context;

import com.example.asus.yaratube.data.UserRepository;
import com.example.asus.yaratube.data.model.SmsResponse;
import com.example.asus.yaratube.data.remote.ApiResult;


public class LoginPhonePresenter implements LoginContract.Presenter {

    LoginContract.View view;
    UserRepository repository;

    public LoginPhonePresenter(LoginContract.View view, Context context) {

        this.view = view;
        repository = new UserRepository(context);
    }

    @Override
    public void onSendPhoneNumber(String phoneNumber, String deviceId, String deviceModel, String deviceOs) {

        repository.sendPhoneNumber(new ApiResult<SmsResponse>() {
            @Override
            public void onSuccess(SmsResponse result) {
                view.showResponseMessage(result.getMessage());
            }

            @Override
            public void onFail(String errorMessage) {

            }
        }, phoneNumber, deviceId, deviceModel, deviceOs);
    }
}
