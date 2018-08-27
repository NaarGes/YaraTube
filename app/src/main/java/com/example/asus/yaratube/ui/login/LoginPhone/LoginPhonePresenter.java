package com.example.asus.yaratube.ui.login.LoginPhone;

import android.content.Context;

import com.example.asus.yaratube.data.local.AppDatabase;
import com.example.asus.yaratube.data.local.UserEntity;
import com.example.asus.yaratube.data.UserRepository;
import com.example.asus.yaratube.data.model.SmsResponse;
import com.example.asus.yaratube.data.remote.ApiResult;


public class LoginPhonePresenter implements LoginPhoneContract.Presenter {

    private LoginPhoneContract.View view;
    private UserRepository repository;
    private Context context;
    private AppDatabase database;

    LoginPhonePresenter(LoginPhoneContract.View view, Context context, AppDatabase database) {

        this.view = view;
        this.context = context;
        this.database = database;
        repository = new UserRepository(context);
    }

    @Override
    public void onSendPhoneNumber(final String phoneNumber, String deviceId, String deviceModel, String deviceOs) {

        // save phone number
        UserEntity userEntity = new UserEntity();
        userEntity.setPhoneNumber(phoneNumber);
        database.userDao().insert(userEntity);

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

    @Override
    public void savePhoneNumber(String phoneNumber) {
        UserEntity userEntity = new UserEntity();
        userEntity.setPhoneNumber(phoneNumber);
        database.userDao().insert(userEntity);
    }
}
