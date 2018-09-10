package com.example.asus.yaratube.ui.login.LoginMethod;

public interface LoginMethodContract {

    interface View {

        void activationDone();
        void dismissDialog();
        void showErrorMessage(String errorMessage);
    }
    interface Presenter {

        void onSendGoogleToken(String tokenId, String deviceId, String deviceOs, String deviceModel
                                    , String name, String email, String photoUrl);
    }
}
