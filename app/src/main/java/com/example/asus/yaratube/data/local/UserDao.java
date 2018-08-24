package com.example.asus.yaratube.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface UserDao {

    @Query("SELECT token FROM user")
    public String getToken();

    @Insert
    void insert(UserEntity userEntity);

    @Delete
    void delete(UserEntity userEntity);

    @Update
    void update(UserEntity userEntity);
}
