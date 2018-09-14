package com.example.asus.yaratube.ui.profile;

import android.net.Uri;

import java.util.Date;

public interface ProfileContract {

    interface View {

        void toast(String message);
    }

    interface Presenter {

        void updateUserInfo(String nickname, String name, String sex, String birthDate, Uri profileUri);
        void sendProfileToServer(String nickname, Date birthDate, String gender, String mobile,
                                 String email, String deviceId, String deviceOs, String deviceModel);

        void Logout();

        String getNickname();
        String getUserName();
        String getUserSex();
        String getUserBirthDate();
        String getProfileUrl();
        Uri getProfileUri();
    }

    interface onChoosePhotoListener {

        void choosePhoto(Uri photoUri);
    }
}
