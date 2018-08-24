package com.example.asus.yaratube.login;

public interface LoginContract {

    interface View {

        void showResponseMessage(String message);
    }

    interface Presenter {

        void onSendPhoneNumber(String phoneNumber, String deviceId, String deviceModel, String deviceOs);
    }

}
