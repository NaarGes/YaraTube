package com.example.asus.yaratube.ui.productlist;


import android.content.Context;
import android.util.Log;

import com.example.asus.yaratube.data.remote.Repository;
import com.example.asus.yaratube.data.model.Category;
import com.example.asus.yaratube.data.model.Product;
import com.example.asus.yaratube.data.remote.ApiResult;

import java.util.List;


public class ProductListPresenter implements ProductListContract.Presenter {

    private ProductListContract.View view;
    private Repository repository;

    ProductListPresenter(ProductListContract.View view, Context context) {

        this.view = view;
        repository = new Repository(context);
    }

    @Override
    public void onLoadFirstPage(int categoryId, int offset) {

        view.showProgressBar();
        repository.getProductList(categoryId, offset, new ApiResult<List<Product>>() {
            @Override
            public void onSuccess(List<Product> products) {

                view.hideProgressBar();
                view.loadFirstPage(products);
            }

            @Override
            public void onFail(String errorMessage) {

                view.hideProgressBar();
                view.showErrorMessage(errorMessage);
            }
        });
    }

    @Override
    public void onLoadNextPage(int categoryId, int offset) {

        repository.getProductList(categoryId, offset, new ApiResult<List<Product>>() {
            @Override
            public void onSuccess(List<Product> products) {

                view.loadNextPage(products);
            }

            @Override
            public void onFail(String errorMessage) {
                view.showErrorMessage(errorMessage);
            }
        });
    }
}
