package com.example.asus.yaratube.data.local;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "user")
public class UserEntity {

    @PrimaryKey
    @NonNull
    private String token = "";

    @NonNull
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
