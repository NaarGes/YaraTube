package com.example.asus.yaratube.login;

import com.example.asus.yaratube.BaseView;

public interface LoginCodeContract {

    interface View extends BaseView {

        void activationDone();
    }

    interface Presenter {

        void onSendVerificationCode(String phoneNumber, String deviceId, int verificationCode);
    }
}
