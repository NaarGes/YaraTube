package com.example.asus.yaratube.data.remote;


public interface ApiResult<T> {

    void onSuccess(T result);
    void onFail(String errorMessage);
}

