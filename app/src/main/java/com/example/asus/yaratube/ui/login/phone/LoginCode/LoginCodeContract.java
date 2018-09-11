package com.example.asus.yaratube.ui.login.phone.LoginCode;

public interface LoginCodeContract {

    interface View {

        void dismissDialog();
        void toast(String message);
    }

    interface Presenter {

        void onSendVerificationCode(String phoneNumber, String deviceId, int verificationCode);
        String phoneNumber();
    }

    interface OTPListener {
        void onOTPReceived(String otp);
    }
}
