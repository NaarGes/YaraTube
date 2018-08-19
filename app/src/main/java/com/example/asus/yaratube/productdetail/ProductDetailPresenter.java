package com.example.asus.yaratube.productdetail;

import android.util.Log;

import com.example.asus.yaratube.data.Repository;
import com.example.asus.yaratube.data.model.Comment;
import com.example.asus.yaratube.data.model.Product;
import com.example.asus.yaratube.data.remote.ApiResult;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class ProductDetailPresenter implements ProductDetailContract.Presenter {

    private ProductDetailContract.View view;
    private Repository repository;

    ProductDetailPresenter(ProductDetailContract.View view) {

        this.view = view;
        repository = new Repository();
    }

    @Override
    public void onLoadComments(Product product) {

        view.showProgressBar();
        repository.getComments(new ApiResult<List<Comment>>() {
            @Override
            public void onSuccess(List<Comment> comments) {

                view.hideProgressBar();
                Log.d(TAG, "onSuccess() called with: comments = [" + comments + "]");
                view.showComments(comments);
            }

            @Override
            public void onFail() {

                //view.hideProgressBar();
                view.showErrorMessage();
            }
        }, product);
    }
}
