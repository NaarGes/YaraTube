package com.example.asus.yaratube.profile;

public interface ProfileContract {

    interface View {

        void toast(String message);
    }

    interface Presenter {

        void updateUserInfo(String name, String sex, String birthDate);

        void Logout();
    }
}
