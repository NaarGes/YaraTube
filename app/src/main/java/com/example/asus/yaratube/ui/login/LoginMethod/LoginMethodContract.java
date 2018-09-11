package com.example.asus.yaratube.ui.login.LoginMethod;

public interface LoginMethodContract {

    interface View {

        void dismissDialog();
        void toast(String message);
    }
    interface Presenter {

        void onSendGoogleToken(String tokenId, String deviceId, String deviceOs, String deviceModel
                                    , String name, String email, String photoUrl);
    }
}
