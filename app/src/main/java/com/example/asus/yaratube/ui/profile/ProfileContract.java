package com.example.asus.yaratube.ui.profile;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.asus.yaratube.data.model.ProfileGetResponse;

import java.io.File;


public interface ProfileContract {

    interface View {

        void toast(String message);
    }

    interface Presenter {

        void fillProfile(EditText nickname, EditText name, Spinner gender, TextView birthDate,
                         ImageView profileImage);
        void updateUserInDatabase(String nickname, String name, String gender, String birthDate,
                                  String profileImagePath);
        void sendProfileToServer(String nickname, String birthDate, String gender);
        void sendProfileImageToServer(File image);
        void getAndUpdateProfile(EditText nickname, Spinner gender, TextView birthDate,
                                 ImageView profileImage);

        void Logout();
    }

    interface onChoosePhotoListener {

        void choosePhoto(String filePath);
    }
}
