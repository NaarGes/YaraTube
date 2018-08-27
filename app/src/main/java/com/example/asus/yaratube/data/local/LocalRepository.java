package com.example.asus.yaratube.data.local;

public class LocalRepository {

    private AppDatabase database;

    public LocalRepository(AppDatabase database) {

        this.database = database;
    }

    public void updateUser(UserEntity userEntity) {

        database.userDao().update(userEntity);
    }

    public boolean isLogin() {

        return database.userDao().getToken() != null;

    }
}
