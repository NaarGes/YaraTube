package com.example.asus.yaratube.ui.login.LoginCode;

import com.example.asus.yaratube.ui.base.BaseView;

public interface LoginCodeContract {

    interface View extends BaseView {

        void activationDone();
        void dismissDialog();
    }

    interface Presenter {

        void onSendVerificationCode(String phoneNumber, String deviceId, int verificationCode);
        String phoneNumber();
    }
}
