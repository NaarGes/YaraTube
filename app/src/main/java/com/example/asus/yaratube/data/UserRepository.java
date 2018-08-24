package com.example.asus.yaratube.data;

import android.content.Context;
import android.widget.Toast;

import com.example.asus.yaratube.data.model.SmsResponse;
import com.example.asus.yaratube.data.remote.ApiClient;
import com.example.asus.yaratube.data.remote.ApiResult;
import com.example.asus.yaratube.data.remote.ApiService;
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

        Call<SmsResponse> call = service.sendSMS(phoneNumber, deviceId, deviceModel, deviceOs);

        if(Util.isNetworkAvailable(context)) {
            call.enqueue(new Callback<SmsResponse>() {
                @Override
                public void onResponse(Call<SmsResponse> call, Response<SmsResponse> response) {
                    if(response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onFail(Util.SERVER_ERROR_MESSAGE);
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

    private void toastNetworkNotAvailable(Context context) {

        Toast.makeText(context, Util.INTERNET_ERROR_MESSAGE, Toast.LENGTH_SHORT).show();
    }
}
