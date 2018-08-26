package com.example.asus.yaratube.ui.login.LoginPhone;

import android.content.Context;

import com.example.asus.yaratube.data.UserRepository;
import com.example.asus.yaratube.data.model.SmsResponse;
import com.example.asus.yaratube.data.remote.ApiResult;


public class LoginPhonePresenter implements LoginPhoneContract.Presenter {

    LoginPhoneContract.View view;
    UserRepository repository;

    LoginPhonePresenter(LoginPhoneContract.View view, Context context) {

        this.view = view;
        repository = new UserRepository(context);
    }

    @Override
    public void onSendPhoneNumber(final String phoneNumber, String deviceId, String deviceModel, String deviceOs) {

        repository.sendPhoneNumber(new ApiResult<SmsResponse>() {
            @Override
            public void onSuccess(SmsResponse response) {

                // TODO show progressbar and "please wait for SMS
                view.smsReceived(phoneNumber);
            }

            @Override
            public void onFail(String errorMessage) {

                view.showErrorMessage(errorMessage);
            }
        }, phoneNumber, deviceId, deviceModel, deviceOs);
    }
}
