package com.example.asus.yaratube.ui.productdetail;

import android.support.v4.app.FragmentManager;

import com.example.asus.yaratube.ui.base.BaseView;
import com.example.asus.yaratube.data.model.Comment;
import com.example.asus.yaratube.data.model.Product;

import java.util.List;

public interface ProductDetailContract {

    interface View extends BaseView {

        void showComments(List<Comment> comments);
        void setProductDetails(Product product);
        void openCommentDialog(int productId);
    }

    interface Presenter {

        void onLoadComments(Product product);
        void onLoadProductDetail(int productId);
        boolean isLogin();
        void login(FragmentManager fragmentManager);
    }
}
