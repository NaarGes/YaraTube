package com.example.asus.yaratube.data.remote;


import android.content.Context;
import android.widget.Toast;

import com.example.asus.yaratube.data.model.Category;
import com.example.asus.yaratube.data.model.Comment;
import com.example.asus.yaratube.data.model.Product;
import com.example.asus.yaratube.data.model.Store;
import com.example.asus.yaratube.util.Util;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private ApiService service;
    private Context context;


    public Repository(Context context) {

        service = ApiClient.getRetrofitInstance().create(ApiService.class);
        this.context = context;
    }

    public void getCategories(final ApiResult<List<Category>> callback) {

        Call<List<Category>> call = service.getCategories();

        if(Util.isNetworkAvailable(context)) {
            call.enqueue(new Callback<List<Category>>() {
                @Override
                public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {

                    if (response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onFail(response.message());
                    }
                }

                @Override
                public void onFailure(Call<List<Category>> call, Throwable t) {

                    callback.onFail(t.getMessage());
                }
            });
        } else {
            toastNetworkNotAvailable(context);
        }
    }

    public void getStore(final ApiResult<Store> callback) {

        Call<Store> call = service.getDashboard();

        if(Util.isNetworkAvailable(context)) {
            call.enqueue(new Callback<Store>() {
                @Override
                public void onResponse(Call<Store> call, Response<Store> response) {

                    if (response.isSuccessful()) {
                        callback.onSuccess(response.body());
                    } else {
                        callback.onFail(response.message());
                    }
                }

                @Override
                public void onFailure(Call<Store> call, Throwable t) {

                    callback.onFail(t.getMessage());
                }
            });
        } else {
            toastNetworkNotAvailable(context);
        }
    }

    public void getProductList(int categoryId, int offset, final ApiResult<List<Product>> callback) {

        Call<List<Product>> call = service.getProductList(categoryId, 10, offset);

        if(Util.isNetworkAvailable(context)) {
            call.enqueue(new Callback<List<Product>>() {
                @Override
                public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                    if (response.isSuccessful()) {

                        callback.onSuccess(response.body());
                    } else {
                        callback.onFail(response.message());
                    }
                }

                @Override
                public void onFailure(Call<List<Product>> call, Throwable t) {

                    callback.onFail(t.getMessage());
                }
            });
        } else {
            toastNetworkNotAvailable(context);
        }
    }


    public void getComments(int productId, int offset, final ApiResult<List<Comment>> callback) {

        Call<List<Comment>> call = service.getComments(productId, 5, offset);

        if(Util.isNetworkAvailable(context)) {
            call.enqueue(new Callback<List<Comment>>() {
                @Override
                public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {

                    if (response.isSuccessful()) {

                        callback.onSuccess(response.body());
                    } else {
                        callback.onFail(response.message());
                    }
                }

                @Override
                public void onFailure(Call<List<Comment>> call, Throwable t) {

                    callback.onFail(t.getMessage());
                }
            });
        } else {
            toastNetworkNotAvailable(context);
        }
    }

    public void getProductDetail(int productId, final ApiResult<Product> callback) {

        Call<Product> call = service.getProductDetail(productId);

        if(Util.isNetworkAvailable(context)) {
            call.enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {

                    if (response.isSuccessful()) {

                        callback.onSuccess(response.body());
                    } else {
                        callback.onFail(response.message());
                    }
                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {
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
