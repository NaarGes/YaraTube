package com.example.asus.yaratube.ui.productlist;


import android.content.Context;

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
    public void onLoadProductList(Category category) {

        view.showProgressBar();

        repository.getProductList(category, new ApiResult<List<Product>>() {
            @Override
            public void onSuccess(List<Product> products) {

                view.hideProgressBar();
                view.showProductList(products);
            }

            @Override
            public void onFail(String errorMessage) {

                view.showErrorMessage(errorMessage);
            }
        });
    }
}
