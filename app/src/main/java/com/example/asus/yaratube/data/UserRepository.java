package com.example.asus.yaratube.data;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

import com.example.asus.yaratube.data.local.AppDatabase;
import com.example.asus.yaratube.data.local.UserEntity;
import com.example.asus.yaratube.data.model.Activation;
import com.example.asus.yaratube.data.model.CommentPostResponse;
import com.example.asus.yaratube.data.model.SmsResponse;
import com.example.asus.yaratube.data.remote.ApiClient;
import com.example.asus.yaratube.data.remote.ApiResult;
import com.example.asus.yaratube.data.remote.ApiService;
import com.example.asus.yaratube.ui.login.LoginDialogFragment;
import com.example.asus.yaratube.util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private ApiService service;
    private Context context;
    private AppDatabase database;


    public UserRepository(Context context) {

        service = ApiClient.getRetrofitInstance().create(ApiService.class);
        this.context = context;
    }

    public void setDatabase(AppDatabase database) {
        this.database = database;
    }

    public void updateUser(UserEntity userEntity) {
        database.userDao().update(userEntity);
    }

    public UserEntity getUser() {
        return database.userDao().getUser();
    }

    public boolean isLogin() {
        return database.userDao().getToken() != null;
    }

    public void login(FragmentManager fragmentManager) {

        LoginDialogFragment loginDialogFragment = LoginDialogFragment.newInstance();
        loginDialogFragment.setCancelable(false);
        loginDialogFragment.show(fragmentManager, loginDialogFragment.getClass().getName());
    }

    public void logout() {
        database.userDao().deleteToken();
    }

    public String phoneNumber() {
        return database.userDao().getPhoneNumber();
    }

    public void sendPhoneNumber(final ApiResult<SmsResponse> callback, String phoneNumber, String deviceId, String deviceModel, String deviceOs) {

        Call<SmsResponse> call = service.activateStep1(phoneNumber, deviceId, deviceModel, deviceOs);

        if(Util.isNetworkAvailable(context)) {
            call.enqueue(new Callback<SmsResponse>() {
                @Override
                public void onResponse(Call<SmsResponse> call, Response<SmsResponse> response) {
                    if(response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onFail(response.message());
                    }
                }

                @Override
                public void onFailure(Call<SmsResponse> call, Throwable t) {

                    callback.onFail(t.getMessage());
                }
            });
        } else {
            toastNetworkNotAvailable(context);
        }
    }

    public void sendVerificationCode(final ApiResult<Activation> callback, String phoneNumber, String deviceId, int verificationCode) {

        Call<Activation> call = service.activateStep2(phoneNumber, deviceId, verificationCode);

        if(Util.isNetworkAvailable(context)) {
            call.enqueue(new Callback<Activation>() {
                @Override
                public void onResponse(Call<Activation> call, Response<Activation> response) {
                    if(response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onFail(response.message());
                    }
                }

                @Override
                public void onFailure(Call<Activation> call, Throwable t) {

                    callback.onFail(t.getMessage());
                }
            });
        }
    }

    public void sendUsercomment(final ApiResult<CommentPostResponse> callback, int score, String commentText, int productId, String token) {

        Call<CommentPostResponse> call = service.sendComment("", score, commentText, productId, token);

        if(Util.isNetworkAvailable(context)) {
            call.enqueue(new Callback<CommentPostResponse>() {
                @Override
                public void onResponse(Call<CommentPostResponse> call, Response<CommentPostResponse> response) {
                    if(response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onFail(response.message());
                    }
                }

                @Override
                public void onFailure(Call<CommentPostResponse> call, Throwable t) {

                    callback.onFail(t.getMessage());
                }
            });
        }
    }

    private void toastNetworkNotAvailable(Context context) {

        Toast.makeText(context, Util.INTERNET_ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
    }
}
