package com.example.asus.yaratube.ui.profile;

import android.content.Context;

import com.example.asus.yaratube.data.UserRepository;
import com.example.asus.yaratube.data.local.AppDatabase;
import com.example.asus.yaratube.data.local.UserEntity;

public class ProfilePresenter implements ProfileContract.Presenter {

    private ProfileContract.View view;
    private AppDatabase database;
    private UserRepository userRepository;
    private UserEntity user;

    ProfilePresenter(Context context, ProfileContract.View view, AppDatabase database) {

        this.view = view;
        this.database = database;
        this.userRepository = new UserRepository(context);
        userRepository.setDatabase(database);

        user = database.userDao().getUser();
    }

    @Override
    public void updateUserInfo(String name, String sex, String birthDate) {

        user.setPhoneNumber(database.userDao().getPhoneNumber());
        user.setToken(database.userDao().getToken());
        user.setName(name);
        user.setSex(sex);
        user.setBirthDate(birthDate);

        userRepository.updateUser(user);

        view.toast("تغییرات با موفقیت اعمال شد");
    }

    @Override
    public void Logout() {

        UserEntity user = database.userDao().getUser();
        database.userDao().delete(user);
        view.toast("شما با موفقیت خارج شدید");
    }

    @Override
    public String getUserName() {

        if(database.userDao().getName() != null) {
            return user.getName();
        }
        return "";
    }

    @Override
    public String getUserSex() {

        if(database.userDao().getSex() != null)
            return user.getSex();
        return "";
    }

    @Override
    public String getUserBirthDate() {

        if(database.userDao().getBirthDate() != null)
            return user.getBirthDate();
        return "";
    }
}
