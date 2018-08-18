package com.example.asus.yaratube.productdetail;

import com.example.asus.yaratube.BaseView;
import com.example.asus.yaratube.data.model.Product;

public interface ProductDetailContract {

    interface View extends BaseView {

        void showProductDetail(Product product);
    }

    interface Presenter {

        void onLoadProductDetail(Product product);
    }
}
