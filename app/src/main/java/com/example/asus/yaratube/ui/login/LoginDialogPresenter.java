package com.example.asus.yaratube.ui.login;

import android.content.Context;

import com.example.asus.yaratube.data.UserRepository;
import com.example.asus.yaratube.data.local.AppDatabase;

public class LoginDialogPresenter implements LoginDialogContract.Presenter {

    private LoginDialogContract.View view;
    private UserRepository repository;

    LoginDialogPresenter(LoginDialogContract.View view, Context context) {

        this.view = view;
        repository = new UserRepository(context);
        repository.setDatabase(AppDatabase.getAppDatabase(context));
    }

    @Override
    public String phoneNumber() {
        return repository.phoneNumber();
    }
}
