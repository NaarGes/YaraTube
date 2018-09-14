package com.example.asus.yaratube.ui.login.LoginMethod;

import android.content.Context;
import android.util.Log;

import com.example.asus.yaratube.data.UserRepository;
import com.example.asus.yaratube.data.local.AppDatabase;
import com.example.asus.yaratube.data.local.UserEntity;
import com.example.asus.yaratube.data.model.GoogleLoginResponse;
import com.example.asus.yaratube.data.remote.ApiResult;

public class LoginMethodPresenter implements LoginMethodContract.Presenter {

    private LoginMethodContract.View view;
    private UserRepository repository;

    LoginMethodPresenter(LoginMethodContract.View view, Context context) {

        this.view = view;
        this.repository = new UserRepository(context);
        repository.setDatabase(AppDatabase.getAppDatabase(context));
    }

    @Override
    public void onSendGoogleToken(String tokenId, String deviceId, String deviceOs, String deviceModel,
                                  final String name, final String email, final String photoUrl) {

        repository.googleLogin(tokenId, deviceId, deviceOs, deviceModel,
                new ApiResult<GoogleLoginResponse>() {
                    @Override
                    public void onSuccess(GoogleLoginResponse result) {

                        view.toast("خوش آمدید");
                        UserEntity userEntity = new UserEntity();
                        userEntity.setToken(result.getToken());
                        userEntity.setName(name);
                        userEntity.setEmail(email);
                        userEntity.setGooglePhotoUrl(photoUrl);

                        if(repository.getUser() != null)
                            repository.updateUser(userEntity);
                        else
                            repository.createUser(userEntity);

                        view.dismissDialog();
                        Log.e("server response", "onSuccess: "+result.getToken()+" "+result.getMessage());
                    }

                    @Override
                    public void onFail(String errorMessage) {

                        view.toast(errorMessage);
                    }
                }
        );
    }
}
