package com.example.asus.yaratube.data;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Toast;

import com.example.asus.yaratube.data.local.AppDatabase;
import com.example.asus.yaratube.data.local.UserEntity;
import com.example.asus.yaratube.data.model.Activation;
import com.example.asus.yaratube.data.model.CommentPostResponse;
import com.example.asus.yaratube.data.model.GoogleLoginResponse;
import com.example.asus.yaratube.data.model.ProfileGetResponse;
import com.example.asus.yaratube.data.model.ProfilePostResponse;
import com.example.asus.yaratube.data.model.SmsResponse;
import com.example.asus.yaratube.data.remote.ApiClient;
import com.example.asus.yaratube.data.remote.ApiResult;
import com.example.asus.yaratube.data.remote.ApiService;
import com.example.asus.yaratube.ui.login.LoginDialogFragment;
import com.example.asus.yaratube.util.Util;


import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
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

    public void createUser(UserEntity userEntity) {
        database.userDao().insert(userEntity);
    }

    public boolean isLogin() {
        return database.userDao().getToken() != null;
    }

    public void login(FragmentManager fragmentManager) {

        Log.e("number of users in db", "login: "+database.userDao().getNumberOfUsers() );
        LoginDialogFragment loginDialogFragment = LoginDialogFragment.newInstance();
        loginDialogFragment.show(fragmentManager, loginDialogFragment.getClass().getName());
    }

    public void logout(UserEntity userEntity) {
        database.userDao().delete(userEntity);
    }

    public String phoneNumber() {
        return database.userDao().getPhoneNumber();
    }

    public String token() {
        return database.userDao().getToken();
    }

    public void sendPhoneNumber(String phoneNumber, String deviceId, String deviceModel, String deviceOs,
                                final ApiResult<SmsResponse> callback) {

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

    public void sendVerificationCode(String phoneNumber, String deviceId, int verificationCode,
                                     final ApiResult<Activation> callback) {

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
        } else {
            toastNetworkNotAvailable(context);
        }
    }

    public void sendUsercomment(int score, String commentText, int productId, String token,
                                final ApiResult<CommentPostResponse> callback) {

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
        } else {
            toastNetworkNotAvailable(context);
        }
    }

    public void googleLogin(String tokenId, String deviceId, String deviceOs, String deviceModel,
                            final ApiResult<GoogleLoginResponse> callback) {

        Call<GoogleLoginResponse> call = service.googleLogin(tokenId, deviceId, deviceOs, deviceModel);

        if(Util.isNetworkAvailable(context)) {
            call.enqueue(new Callback<GoogleLoginResponse>() {
                @Override
                public void onResponse(Call<GoogleLoginResponse> call, Response<GoogleLoginResponse> response) {
                    if(response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onFail(response.message());
                    }
                }

                @Override
                public void onFailure(Call<GoogleLoginResponse> call, Throwable t) {

                    callback.onFail(t.getMessage());
                }
            });
        } else {
            toastNetworkNotAvailable(context);
        }
    }

    public ProfileGetResponse getProfile(String token, final ApiResult<ProfileGetResponse> callback) {

        Call<ProfileGetResponse> call = service.getProfile("Token " + token);
        Log.d("token for get profile", "getProfile: "+token);

        if (Util.isNetworkAvailable(context)) {
            call.enqueue(new Callback<ProfileGetResponse>() {
                @Override
                public void onResponse(Call<ProfileGetResponse> call, Response<ProfileGetResponse> response) {
                    if (response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onFail(response.message());
                    }
                }

                @Override
                public void onFailure(Call<ProfileGetResponse> call, Throwable t) {

                    callback.onFail(t.getMessage());
                }
            });
        } else {
            toastNetworkNotAvailable(context);
        }
        return null;
    }

    public void sendProfile(String nickname, String birthDate, String gender,
                            String tokenId, final ApiResult<ProfilePostResponse> callback) {

        Call<ProfilePostResponse> call = service.sendProfile(nickname, birthDate, gender, "Token " + tokenId);
        Log.d("info sent to server", "sendProfile: "+nickname+" "+birthDate+ " "+gender+" "+tokenId);

        if (Util.isNetworkAvailable(context)) {
            call.enqueue(new Callback<ProfilePostResponse>() {
                @Override
                public void onResponse(Call<ProfilePostResponse> call, Response<ProfilePostResponse> response) {
                    if(response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onFail(response.message());
                    }
                }

                @Override
                public void onFailure(Call<ProfilePostResponse> call, Throwable t) {
                    callback.onFail(t.getMessage());
                }
            });
        } else {
            toastNetworkNotAvailable(context);
        }
    }

    public void sendProfileImage(File image, String token, final ApiResult<ProfilePostResponse> callback) {

        RequestBody reqFile = RequestBody.create(MediaType.parse("image/*"), image);
        MultipartBody.Part body = MultipartBody.Part.createFormData("avatar", image.getName(), reqFile);

        Call<ProfilePostResponse> call = service.sendProfileImage(body, "Token " + token);
        Log.e("info sent to server", "sendProfileImage: " + body + " " + token);

        if (Util.isNetworkAvailable(context)) {
            call.enqueue(new Callback<ProfilePostResponse>() {
                @Override
                public void onResponse(Call<ProfilePostResponse> call, Response<ProfilePostResponse> response) {
                    if (response.isSuccessful())
                        callback.onSuccess(response.body());
                    else
                        callback.onFail(response.message());
                }

                @Override
                public void onFailure(Call<ProfilePostResponse> call, Throwable t) {

                    callback.onFail(t.getMessage());
                }
            });
        } else {
            toastNetworkNotAvailable(context);
        }
    }

    private void toastNetworkNotAvailable(Context context) {

        Toast.makeText(context, Util.INTERNET_ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
    }
}
