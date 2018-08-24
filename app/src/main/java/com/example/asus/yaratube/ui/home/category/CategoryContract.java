package com.example.asus.yaratube.ui.home.category;

import com.example.asus.yaratube.data.model.Category;
import com.example.asus.yaratube.ui.base.BaseView;

import java.util.List;

public interface CategoryContract {

    interface View extends BaseView {

        void showCategoryList(List<Category> categories);
    }

    interface Presenter {

        void onLoadCategory();
    }

    interface onCategoryClickListener {

        void onCategoryClick(Category category);
    }

}
