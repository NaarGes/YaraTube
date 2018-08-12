package com.example.asus.yaratube.data;

import com.example.asus.yaratube.data.model.Category;
import com.example.asus.yaratube.data.remote.ApiClient;
import com.example.asus.yaratube.data.remote.ApiResult;
import com.example.asus.yaratube.data.remote.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private ApiService service;


    public Repository() {

        service = ApiClient.getRetrofitInstance().create(ApiService.class);
    }

    public void getCategories(final ApiResult.CategoryResult callback) {

        Call<List<Category>> call = service.getCategories();

        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {

                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFail();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

                callback.onFail();
            }
        });
    }
}
