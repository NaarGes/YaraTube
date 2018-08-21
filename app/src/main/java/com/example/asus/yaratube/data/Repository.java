package com.example.asus.yaratube.data;


import com.example.asus.yaratube.data.model.Category;
import com.example.asus.yaratube.data.model.Comment;
import com.example.asus.yaratube.data.model.Product;
import com.example.asus.yaratube.data.model.Store;
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

    public void getCategories(final ApiResult<List<Category>> callback) {

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

    public void getStore(final ApiResult<Store> callback) {

        Call<Store> call = service.getDashboard();
        call.enqueue(new Callback<Store>() {
            @Override
            public void onResponse(Call<Store> call, Response<Store> response) {

                if(response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFail();
                }
            }

            @Override
            public void onFailure(Call<Store> call, Throwable t) {

                callback.onFail();
                t.printStackTrace();
            }
        });
    }

    public void getProductList(final ApiResult<List<Product>> callback, Category category) {

        Call<List<Product>> call = service.getProductList(category.getId());
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                if(response.isSuccessful()) {

                    callback.onSuccess(response.body());
                } else {
                    callback.onFail();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {

                callback.onFail();
            }
        });
    }


    public void getComments(final ApiResult<List<Comment>> callback, Product product) {

        Call<List<Comment>> call = service.getComments(product.getId());
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                if(response.isSuccessful()) {

                    callback.onSuccess(response.body());
                } else {
                    callback.onFail();
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {

                callback.onFail();
            }
        });
    }

    public void getProductDetail(final ApiResult<Product> callback, int productId) {

        Call<Product> call = service.getProductDetail(productId);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {

                if(response.isSuccessful()) {

                    callback.onSuccess(response.body());
                } else {
                    callback.onFail();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                callback.onFail();
            }
        });
    }
}
