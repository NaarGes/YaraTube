package com.example.asus.yaratube.ui.productdetail.comment;

import android.content.Context;

import com.example.asus.yaratube.data.UserRepository;
import com.example.asus.yaratube.data.local.AppDatabase;
import com.example.asus.yaratube.data.model.CommentPostResponse;
import com.example.asus.yaratube.data.remote.ApiResult;

public class CommentPresenter implements CommentContract.Presenter {

    private CommentContract.View view;
    private UserRepository repository;

    CommentPresenter(CommentContract.View view, Context context, AppDatabase database) {

        this.view = view;
        this.repository = new UserRepository(context);
        repository.setDatabase(database);
    }

    @Override
    public void sendCommentToServer(int score, String comment, int productId, String token) {

        repository.sendUsercomment(new ApiResult<CommentPostResponse>() {
            @Override
            public void onSuccess(CommentPostResponse result) {

                view.showMessage("نظر شما پس از بررسی ثبت خواهد شد");
                view.dissmissDialog();
            }

            @Override
            public void onFail(String errorMessage) {

                view.showMessage(errorMessage);
            }
        }, score, comment, productId, token);
    }

    @Override
    public String getToken() {

        return repository.getUser().getToken();
    }
}
