package com.example.asus.yaratube.ui.login;

public interface LoginDialogContract {

    interface steps {

        void goToLoginPhone();
        void goToLoginCode(String phoneNumber);
    }
}
