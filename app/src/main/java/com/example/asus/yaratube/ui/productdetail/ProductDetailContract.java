package com.example.asus.yaratube.ui.productdetail;

import android.support.v4.app.FragmentManager;

import com.example.asus.yaratube.ui.base.BaseView;
import com.example.asus.yaratube.data.model.Comment;
import com.example.asus.yaratube.data.model.Product;

import java.util.List;

public interface ProductDetailContract {

    interface View extends BaseView {

        void showFirstComments(List<Comment> comments);
        void showNextComments(List<Comment> comments);
        void setProductDetails(Product product);
        void openCommentDialog(int productId);
    }

    interface Presenter {

        void onLoadFirstComments(int productId, int offset);
        void onLoadNextComments(int productId, int offset);
        void onLoadProductDetail(int productId);
        boolean isLogin();
        void login(FragmentManager fragmentManager);
    }
}
