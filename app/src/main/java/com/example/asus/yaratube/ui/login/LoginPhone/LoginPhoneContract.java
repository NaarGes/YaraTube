package com.example.asus.yaratube.ui.login.LoginPhone;

import com.example.asus.yaratube.ui.base.BaseView;

public interface LoginPhoneContract {

    interface View extends BaseView {

        void smsReceived(String phoneNumber);
    }

    interface Presenter {

        void onSendPhoneNumber(String phoneNumber, String deviceId, String deviceModel, String deviceOs);
    }
}
