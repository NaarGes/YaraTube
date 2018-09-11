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

        user = userRepository.getUser();
    }

    @Override
    public void updateUserInfo(String nickname, String name, String sex, String birthDate) {

        user.setPhoneNumber(database.userDao().getPhoneNumber());
        user.setToken(database.userDao().getToken());
        user.setNickname(nickname);
        user.setName(name);
        user.setSex(sex);
        user.setBirthDate(birthDate);

        userRepository.updateUser(user);

        view.toast("تغییرات با موفقیت اعمال شد");
    }

    @Override
    public void Logout() {

        userRepository.logout(userRepository.getUser());
        view.toast("شما با موفقیت خارج شدید");
    }

    @Override
    public String getNickname() {

        if(user.getNickname() != null)
            return user.getNickname();
        return "";
    }


    @Override
    public String getUserName() {

        if(user.getName() != null)
            return user.getName();
        return "";
    }

    @Override
    public String getUserSex() {

        if(user.getSex() != null)
            return user.getSex();
        return "";
    }

    @Override
    public String getUserBirthDate() {

        if(user.getBirthDate() != null)
            return user.getBirthDate();
        return "";
    }

    @Override
    public String getProfileUrl() {

        if(user.getPhotoUrl() != null)
            return user.getPhotoUrl();
        return "";
    }
}
