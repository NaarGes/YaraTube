package com.example.asus.yaratube.ui.login;

public interface LoginDialogContract {

    interface View {

    }

    interface Presenter {

        String phoneNumber();
    }

    interface steps {

        void goToLoginPhone();
        void goToLoginCode(String phoneNumber);
    }
}
