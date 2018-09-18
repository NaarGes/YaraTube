package com.example.asus.yaratube.ui.home.category;

import android.content.Context;

import com.example.asus.yaratube.data.remote.Repository;
import com.example.asus.yaratube.data.model.Category;
import com.example.asus.yaratube.data.remote.ApiResult;

import java.util.List;

public class CategoryPresenter implements CategoryContract.Presenter {

    private CategoryContract.View view;
    private Repository repository;

    CategoryPresenter(CategoryContract.View view, Context context) {

        this.view = view;
        repository = new Repository(context);
    }

    @Override
    public void onLoadCategory() {

        view.showProgressBar();

        repository.getCategories(new ApiResult<List<Category>>() {
            @Override
            public void onSuccess(List<Category> categories) {

                view.hideProgressBar();
                view.showCategoryList(categories);
            }

            @Override
            public void onFail(String errorMessage) {

                view.hideProgressBar();
                view.showSnackbar();
            }
        });
    }
}
