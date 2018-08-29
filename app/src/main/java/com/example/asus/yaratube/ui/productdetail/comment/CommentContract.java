package com.example.asus.yaratube.ui.productdetail.comment;


public interface CommentContract {

    interface View {

        void showMessage(String errorMessage);
        void dissmissDialog();
    }

    interface Presenter {

        void sendCommentToServer(int score, String comment, int productId, String token);
        String getToken();
    }

}
