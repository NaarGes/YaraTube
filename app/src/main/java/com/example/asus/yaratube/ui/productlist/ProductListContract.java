package com.example.asus.yaratube.ui.productlist;

import com.example.asus.yaratube.ui.base.BaseView;
import com.example.asus.yaratube.data.model.Category;
import com.example.asus.yaratube.data.model.Product;

import java.util.List;

public interface ProductListContract {

    interface View extends BaseView {

        void loadFirstPage(List<Product> products);
        void loadNextPage(List<Product> products);
    }

    interface Presenter {

        void onLoadFirstPage(int categoryId, int offset);
        void onLoadNextPage(int categoryId, int offset);
    }

    interface onProductClickListener {

        void onProductClick(Product product);
    }
}
