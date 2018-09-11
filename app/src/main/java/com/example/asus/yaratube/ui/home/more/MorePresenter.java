package com.example.asus.yaratube.ui.home.more;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import com.example.asus.yaratube.data.UserRepository;
import com.example.asus.yaratube.data.local.AppDatabase;

public class MorePresenter {

    private UserRepository repository;

    MorePresenter(Context context) {
        repository = new UserRepository(context);
        repository.setDatabase(AppDatabase.getAppDatabase(context));
    }

    public boolean isLogin() {
        return repository.isLogin();
    }

    public void login(FragmentManager fragmentManager) {
        repository.login(fragmentManager);
    }
}
