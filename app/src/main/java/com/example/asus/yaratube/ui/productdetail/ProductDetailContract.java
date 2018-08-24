package com.example.asus.yaratube.ui.productdetail;

import com.example.asus.yaratube.ui.base.BaseView;
import com.example.asus.yaratube.data.model.Comment;
import com.example.asus.yaratube.data.model.Product;

import java.util.List;

public interface ProductDetailContract {

    interface View extends BaseView {

        void showComments(List<Comment> comments);
        void setProductDetails(Product product);
    }

    interface Presenter {

        void onLoadComments(Product product);
        void onLoadProductDetail(int productId);
    }
}
