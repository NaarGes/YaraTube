package com.example.asus.yaratube.data;

import com.example.asus.yaratube.data.local.AppDatabase;
import com.example.asus.yaratube.data.local.UserEntity;

public class LocalRepository {

    private AppDatabase database;

    public LocalRepository(AppDatabase database) {

        this.database = database;
    }

    public void loginUser(UserEntity userEntity) {

        database.userDao().insert(userEntity);
    }

    public void updateUser(UserEntity userEntity) {

        database.userDao().update(userEntity);
    }

    public boolean isLogin() {

        return database.userDao().getToken() != null;

    }


}
