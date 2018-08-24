package com.example.asus.yaratube.profile;

public interface ProfileContract {

    interface View {

        void changesSubmitted();
    }

    interface Presenter {

        void onSubmitChanges(String name, String sex, String birthDate);
    }
}
