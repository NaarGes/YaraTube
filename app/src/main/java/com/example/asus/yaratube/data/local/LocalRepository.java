package com.example.asus.yaratube.data.local;

public class LocalRepository {

    private AppDatabase database;

    public LocalRepository(AppDatabase database) {
        this.database = database;
    }

    public void updateUser(UserEntity userEntity) {
        database.userDao().update(userEntity);
    }

    public UserEntity getUser() {
        return database.userDao().getUser();
    }

    public boolean isLogin() {
        return database.userDao().getToken() != null;
    }

    public String phoneNumber() {
        return database.userDao().getPhoneNumber();
    }
}
