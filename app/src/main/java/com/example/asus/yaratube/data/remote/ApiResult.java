package com.example.asus.yaratube.data.remote;

import com.example.asus.yaratube.data.model.Category;

import java.util.List;

// FIXME make it generic
public interface ApiResult {

    interface CategoryResult {

        void onSuccess(List<Category> categories);
        void onFail();
    }

}
