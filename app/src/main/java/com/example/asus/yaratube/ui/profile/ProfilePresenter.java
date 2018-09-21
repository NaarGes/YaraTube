package com.example.asus.yaratube.ui.profile;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.asus.yaratube.data.UserRepository;
import com.example.asus.yaratube.data.local.AppDatabase;
import com.example.asus.yaratube.data.local.UserEntity;
import com.example.asus.yaratube.data.model.ProfileGetResponse;
import com.example.asus.yaratube.data.model.ProfilePostResponse;
import com.example.asus.yaratube.data.remote.ApiResult;
import com.example.asus.yaratube.util.Util;

import java.io.File;

import static com.example.asus.yaratube.util.Util.BASE_URL;


public class ProfilePresenter implements ProfileContract.Presenter {

    private ProfileContract.View view;
    private AppDatabase database;
    private UserRepository userRepository;
    private UserEntity user;
    private Context context;

    ProfilePresenter(Context context, ProfileContract.View view) {

        this.view = view;
        this.context = context;
        this.userRepository = new UserRepository(context);
        this.database = AppDatabase.getAppDatabase(context);
        userRepository.setDatabase(database);
        user = userRepository.getUser();

    }

    @Override
    public void fillProfile(EditText nickname, EditText name, Spinner gender, TextView birthDate,
                            ImageView profileImage) {

        // first read from database, then fetch data from server and compare to database,
        // if it was different, update ir
        nickname.setText(user.getNickname());
        name.setText(user.getName());
        birthDate.setText(user.getBirthDate());

        if (user.getSex() != null && user.getSex().equals("Female"))
            gender.setSelection(0);
        else if (user.getSex() != null && user.getSex().equals("Male"))
            gender.setSelection(1);

        if (user.getPhotoUri() != null)
            Glide.with(context)
                    .load(user.getPhotoUri())
                    .apply(RequestOptions.circleCropTransform())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(profileImage);

        if(Util.isNetworkAvailable(context))
            getAndUpdateProfile(nickname, gender, birthDate, profileImage);

    }

    private String formatBirthDate(String dateOfBirth) {
        return dateOfBirth.replace("-", "/");
    }

    @Override
    public void updateUserInDatabase(String nickname, String name, String gender, String birthDate,
                               String profileImagePath) {

        user.setPhoneNumber(database.userDao().getPhoneNumber());
        user.setToken(database.userDao().getToken());
        user.setNickname(nickname);
        user.setName(name);
        user.setSex(gender);
        user.setBirthDate(Util.enToFa(birthDate));
        if (profileImagePath != null) {
            Log.e("photo is saving in db", "updateUserInfo: " + profileImagePath);
            user.setPhotoUri(profileImagePath);
        } else
            user.setPhotoUri(database.userDao().getPhotoUri());
        userRepository.updateUser(user);

        view.toast("تغییرات با موفقیت اعمال شد");
    }

    @Override
    public void sendProfileToServer(String nickname, String birthDate, String gender) {

        userRepository.sendProfile(nickname, birthDate, gender, getTokenId(),
                new ApiResult<ProfilePostResponse>() {
            @Override
            public void onSuccess(ProfilePostResponse result) {

                Log.e("nickname get in res", "onSuccess: " + result.getData().getNickname());
                Log.e("gender get in res", "onSuccess: " + result.getData().getGender());
                Log.e("date get in res", "onSuccess: " + result.getData().getDateOfBirth());
            }

            @Override
            public void onFail(String errorMessage) {

                Log.e("Data sent failed", "onFail: " + errorMessage);
            }
        });
    }

    @Override
    public void sendProfileImageToServer(File image) {

        userRepository.sendProfileImage(image, getTokenId(), new ApiResult<ProfilePostResponse>() {
            @Override
            public void onSuccess(ProfilePostResponse result) {

                Log.e("image", "onSuccess: " + BASE_URL + result.getData().getAvatar());
            }

            @Override
            public void onFail(String errorMessage) {

                Log.e("send image failed", "onFail: error: " + errorMessage);
            }
        });
    }

    @Override
    public void getAndUpdateProfile(final EditText nickname, final Spinner gender, final TextView birthDate,
                                    final ImageView profileImage) {

        userRepository.getProfile(getTokenId(), new ApiResult<ProfileGetResponse>() {
            @Override
            public void onSuccess(ProfileGetResponse result) {

                Log.e("get profile succeed", "onSuccess: "+result.getNickname() + " " +
                        result.getGender() + " " + result.getAvatar() + " " + result.getDateOfBirth());

                // update nickname
                if (result.getNickname() != null &&
                        !result.getNickname().equals(nickname.getText().toString()))
                    nickname.setText(result.getNickname());


                // update gender
                if (result.getGender() != null) {
                    if (result.getGender().equals("Female"))
                        gender.setSelection(0);
                    else if (result.getGender().equals("Male"))
                        gender.setSelection(1);
                }

                // update birth date
                if (result.getDateOfBirth() != null &&
                        !result.getDateOfBirth().equals(birthDate.getText().toString()))
                    birthDate.setText(Util.enToFa(formatBirthDate(result.getDateOfBirth())));


                // update avatar
                if (result.getAvatar() != null)
                    Glide.with(context)
                            .load(BASE_URL + result.getAvatar())
                            .apply(RequestOptions.circleCropTransform())
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .into(profileImage);
            }

            @Override
            public void onFail(String errorMessage) {

                Log.e("get profile failed", "onFail: error: " + errorMessage );
            }
        });
    }


    @Override
    public void Logout() {

        userRepository.logout(userRepository.getUser());
        view.toast("شما با موفقیت خارج شدید");
    }

    private String getTokenId () {

        return user.getToken();
    }
}
