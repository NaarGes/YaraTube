package com.example.asus.yaratube.ui.profile;

import com.example.asus.yaratube.data.local.AppDatabase;
import com.example.asus.yaratube.data.local.UserEntity;

public class ProfilePresenter implements ProfileContract.Presenter {

    private ProfileContract.View view;
    private AppDatabase database;

    ProfilePresenter(ProfileContract.View view, AppDatabase database) {

        this.view = view;
        this.database = database;
    }

    @Override
    public void updateUserInfo(String name, String sex, String birthDate) {

        UserEntity user = new UserEntity();

        user.setToken(database.userDao().getToken());
        user.setName(name);
        user.setSex(sex);
        user.setBirthDate(birthDate);

        database.userDao().update(user);

        view.toast("تغییرات با موفقیت اعمال شد");
    }

    @Override
    public void Logout() {

        UserEntity user = database.userDao().getUser();
        database.userDao().delete(user);
        view.toast("شما با موفقیت خارج شدید");
    }
}
