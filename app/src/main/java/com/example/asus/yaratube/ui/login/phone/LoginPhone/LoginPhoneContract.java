package com.example.asus.yaratube.ui.login.phone.LoginPhone;

public interface LoginPhoneContract {

    interface View {

        void showErrorMessage(String errorMessage);
        void smsReceived(String phoneNumber);
    }

    interface Presenter {

        void onSendPhoneNumber(String phoneNumber, String deviceId, String deviceModel, String deviceOs);
        void savePhoneNumber(String phoneNumber);
    }
}
