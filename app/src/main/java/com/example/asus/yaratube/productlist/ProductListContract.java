package com.example.asus.yaratube.productlist;

import com.example.asus.yaratube.BaseView;
import com.example.asus.yaratube.data.model.Category;
import com.example.asus.yaratube.data.model.Product;

import java.util.List;

public interface ProductListContract {

    interface View extends BaseView {

        void showProductList(List<Product> products);
    }

    interface Presenter {

        void onLoadProductList(Category category);
    }

    interface onProductClickListener {

        void onProductClick(Product product);
    }
}
