package com.example.asus.yaratube.ui.profile;

import android.net.Uri;

public interface ProfileContract {

    interface View {

        void toast(String message);
    }

    interface Presenter {

        void updateUserInfo(String nickname, String name, String sex, String birthDate);

        void Logout();

        String getNickname();
        String getUserName();
        String getUserSex();
        String getUserBirthDate();
        String getProfileUrl();
    }

    interface onChoosePhotoListener {

        void choosePhoto(Uri photoUri);
    }
}
