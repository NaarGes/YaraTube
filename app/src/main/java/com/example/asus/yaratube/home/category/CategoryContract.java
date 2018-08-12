package com.example.asus.yaratube.home.category;

import com.example.asus.yaratube.data.model.Category;

import java.util.List;

public interface CategoryContract {

    interface View {

        void showProgressBar();
        void hideProgressBar();

        void showCategoryList(List<Category> categories);
        void showErrorMessage(); // TODO different error for different states
    }

    interface Presenter {

        void onLoadCategory();

    }

}
