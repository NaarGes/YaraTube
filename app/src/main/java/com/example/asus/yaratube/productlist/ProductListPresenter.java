package com.example.asus.yaratube.productlist;

import android.util.Log;

import com.example.asus.yaratube.data.Repository;
import com.example.asus.yaratube.data.model.Product;
import com.example.asus.yaratube.data.remote.ApiResult;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class ProductListPresenter implements ProductListContract.Presenter {

    private ProductListContract.View view;
    private Repository repository;

    ProductListPresenter(ProductListContract.View view) {

        this.view = view;
        repository = new Repository();
    }

    @Override
    public void onLoadProductList(int categoryId) {

        view.showProgressBar();

        repository.getProductList(new ApiResult<List<Product>>() {
            @Override
            public void onSuccess(List<Product> products) {

                view.hideProgressBar();
                view.showProductList(products);
            }

            @Override
            public void onFail() {

                view.showErrorMessage();
            }
        }, categoryId);
    }
}
