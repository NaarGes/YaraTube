package com.example.asus.yaratube.ui.profile;

import java.io.File;


public interface ProfileContract {

    interface View {

        void toast(String message);
    }

    interface Presenter {

        void updateUserInfo(String nickname, String name, String sex, String birthDate, String profileImagePath);
        void sendProfileToServer(String nickname, String birthDate, String gender);
        void sendProfileImageToServer(File image);

        void Logout();

        String getNickname();
        String getUserName();
        String getUserSex();
        String getUserBirthDate();
        String getProfileUrl();
        String getProfileUri();
    }

    interface onChoosePhotoListener {

        void choosePhoto(String filePath);
    }
}
