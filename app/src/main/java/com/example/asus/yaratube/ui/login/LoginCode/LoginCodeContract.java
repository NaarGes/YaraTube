package com.example.asus.yaratube.ui.login.LoginCode;

public interface LoginCodeContract {

    interface View {

        void activationDone();
        void dismissDialog();
        void showErrorMessage(String errorMessage);
    }

    interface Presenter {

        void onSendVerificationCode(String phoneNumber, String deviceId, int verificationCode);
        String phoneNumber();
    }

    interface OTPListener {
        void onOTPReceived(String otp);
    }
}
