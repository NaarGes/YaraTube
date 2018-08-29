package com.example.asus.yaratube.ui.productdetail;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.example.asus.yaratube.data.UserRepository;
import com.example.asus.yaratube.data.local.AppDatabase;
import com.example.asus.yaratube.data.remote.Repository;
import com.example.asus.yaratube.data.model.Comment;
import com.example.asus.yaratube.data.model.Product;
import com.example.asus.yaratube.data.remote.ApiResult;
import com.example.asus.yaratube.ui.login.LoginDialogFragment;

import java.util.List;

import static android.support.constraint.Constraints.TAG;

public class ProductDetailPresenter implements ProductDetailContract.Presenter {

    private ProductDetailContract.View view;
    private Repository repository;
    private UserRepository userRepository;

    ProductDetailPresenter(ProductDetailContract.View view, Context context, AppDatabase database) {

        this.view = view;
        repository = new Repository(context);
        userRepository = new UserRepository(context);
        userRepository.setDatabase(database);
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
            public void onFail(String errorMessage) {

                //view.hideProgressBar();
                view.showErrorMessage(errorMessage);
            }
        }, product);
    }

    @Override
    public void onLoadProductDetail(int productId) {

        repository.getProductDetail(new ApiResult<Product>() {
            @Override
            public void onSuccess(Product product) {

                view.setProductDetails(product);
            }

            @Override
            public void onFail(String errorMessage) {

                view.showErrorMessage(errorMessage);
            }
        }, productId);
    }

    @Override
    public boolean isLogin() {

        return userRepository.isLogin();
    }

    @Override
    public void login(FragmentManager fragmentManager) {
        userRepository.login(fragmentManager);

    }


}
