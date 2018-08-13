package com.example.asus.yaratube.home.category;

import com.example.asus.yaratube.data.model.Category;
import com.example.asus.yaratube.BaseView;

import java.util.List;

public interface CategoryContract {

    interface View extends BaseView {

        void showCategoryList(List<Category> categories);
    }

    interface Presenter {

        void onLoadCategory();
    }

    interface onCategoryClickListener {

        void onCategoryClick(int CategoryID);
    }

}
