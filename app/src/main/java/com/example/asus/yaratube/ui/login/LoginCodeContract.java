package com.example.asus.yaratube.ui.login;

import com.example.asus.yaratube.ui.base.BaseView;

public interface LoginCodeContract {

    interface View extends BaseView {

        void activationDone();
    }

    interface Presenter {

        void onSendVerificationCode(String phoneNumber, String deviceId, int verificationCode);
    }
}
