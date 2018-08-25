package com.example.asus.yaratube.ui.profile;

import com.example.asus.yaratube.data.LocalRepository;
import com.example.asus.yaratube.data.local.AppDatabase;
import com.example.asus.yaratube.data.local.UserEntity;

public class ProfilePresenter implements ProfileContract.Presenter {

    private ProfileContract.View view;
    private AppDatabase database;
    private LocalRepository localRepository;
    private UserEntity user;

    ProfilePresenter(ProfileContract.View view, AppDatabase database) {

        this.view = view;
        this.database = database;
        this.localRepository = new LocalRepository(database);

        user = database.userDao().getUser();
    }

    @Override
    public void updateUserInfo(String name, String sex, String birthDate) {

        UserEntity user = new UserEntity();

        user.setToken(database.userDao().getToken());
        user.setName(name);
        user.setSex(sex);
        user.setBirthDate(birthDate);

        localRepository.updateUser(user);

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
