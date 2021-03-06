package com.example.asus.yaratube.ui.login.phone.LoginPhone;

import android.content.Context;
import android.util.Log;

import com.example.asus.yaratube.data.local.AppDatabase;
import com.example.asus.yaratube.data.local.UserEntity;
import com.example.asus.yaratube.data.UserRepository;
import com.example.asus.yaratube.data.model.SmsResponse;
import com.example.asus.yaratube.data.remote.ApiResult;


public class LoginPhonePresenter implements LoginPhoneContract.Presenter {

    private LoginPhoneContract.View view;
    private UserRepository repository;
    private AppDatabase database;

    LoginPhonePresenter(LoginPhoneContract.View view, Context context) {

        this.view = view;
        this.database = AppDatabase.getAppDatabase(context);
        repository = new UserRepository(context);
        repository.setDatabase(database);
    }

    @Override
    public void onSendPhoneNumber(final String phoneNumber, String deviceId, String deviceModel, String deviceOs) {

        repository.sendPhoneNumber(phoneNumber, deviceId, deviceModel, deviceOs,
                new ApiResult<SmsResponse>() {
            @Override
            public void onSuccess(SmsResponse response) {

                view.smsReceived(phoneNumber);
            }

            @Override
            public void onFail(String errorMessage) {

                view.showErrorMessage(errorMessage);
            }
        });
    }

    @Override
    public void savePhoneNumber(String phoneNumber) {

        Log.e("number of users", "savePhoneNumber: "+database.userDao().getNumberOfUsers());
        Log.e("user in db", ""+database.userDao().getUser());

        UserEntity userEntity = new UserEntity();
        userEntity.setPhoneNumber(phoneNumber);
        repository.createUser(userEntity);
    }
}
