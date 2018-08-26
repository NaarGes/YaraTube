package com.example.asus.yaratube.data.remote;

import android.content.Context;
import android.widget.Toast;

import com.example.asus.yaratube.data.model.Activation;
import com.example.asus.yaratube.data.model.SmsResponse;
import com.example.asus.yaratube.util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private ApiService service;
    private Context context;


    public UserRepository(Context context) {

        service = ApiClient.getRetrofitInstance().create(ApiService.class);
        this.context = context;
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

    private void toastNetworkNotAvailable(Context context) {

        Toast.makeText(context, Util.INTERNET_ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
    }
}
