package com.example.asus.yaratube.ui.profile;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.example.asus.yaratube.data.UserRepository;
import com.example.asus.yaratube.data.local.AppDatabase;
import com.example.asus.yaratube.data.local.UserEntity;
import com.example.asus.yaratube.data.model.ProfilePostResponse;
import com.example.asus.yaratube.data.remote.ApiResult;

import java.util.Date;

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
    public void updateUserInfo(String nickname, String name, String sex, String birthDate, Uri profileUri) {

        user.setPhoneNumber(database.userDao().getPhoneNumber());
        user.setToken(database.userDao().getToken());
        user.setNickname(nickname);
        user.setName(name);
        user.setSex(sex);
        user.setBirthDate(birthDate);
        Log.e("photo is saving", "updateUserInfo: "+profileUri );
        if(profileUri != null)
            user.setPhotoUri(profileUri.toString());

        userRepository.updateUser(user);

        view.toast("تغییرات با موفقیت اعمال شد");
    }

    @Override
    public void sendProfileToServer(String nickname, Date birthDate, String gender, String mobile,
                                    String email, String deviceId, String deviceOs, String deviceModel) {

        userRepository.sendProfile(nickname, birthDate, gender, mobile, email, deviceId, deviceOs,
                deviceModel, new ApiResult<ProfilePostResponse>() {
            @Override
            public void onSuccess(ProfilePostResponse result) {

                Log.d("Data successfully sent", "onSuccess: " + result.getError() + " " + result.getMessage());
            }

            @Override
            public void onFail(String errorMessage) {

                // fixme UNAUTHORIZED
                Log.d("Data sent failed", "onFail: " + errorMessage);
            }
        });
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

        if(user.getGooglePhotoUrl() != null)
            return user.getGooglePhotoUrl();
        return "";
    }

    @Override
    public Uri getProfileUri() {

        if(user.getPhotoUri() != null)
            return Uri.parse(user.getPhotoUri());
        return null;
    }
}
