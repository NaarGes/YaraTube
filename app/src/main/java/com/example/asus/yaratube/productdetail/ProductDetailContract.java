package com.example.asus.yaratube.productdetail;

import com.example.asus.yaratube.BaseView;
import com.example.asus.yaratube.data.model.Comment;
import com.example.asus.yaratube.data.model.Product;

import java.util.List;

public interface ProductDetailContract {

    interface View extends BaseView {

        void showComments(List<Comment> comments);
    }

    interface Presenter {

        void onLoadComments(Product product);
    }
}
