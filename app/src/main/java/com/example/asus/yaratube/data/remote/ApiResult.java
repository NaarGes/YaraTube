package com.example.asus.yaratube.data.remote;

import com.example.asus.yaratube.data.model.Category;
import com.example.asus.yaratube.data.model.Store;

import java.util.List;

// FIXME make it generic
public interface ApiResult {

    interface CategoryResult {

        void onSuccess(List<Category> categories);
        void onFail();
    }

    interface StoreResult {

        void onSuccess(Store store);
        void onFail();
    }
}
