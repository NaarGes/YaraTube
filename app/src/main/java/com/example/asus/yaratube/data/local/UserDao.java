package com.example.asus.yaratube.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface UserDao {

    @Query("SELECT token FROM user")
    String getToken();

    @Query("SELECT name FROM user")
    String getName();

    @Query("SELECT sex FROM user")
    String getSex();

    @Query("SELECT birth_date FROM user")
    String getBirthDate();

    @Query("SELECT phone_number FROM user")
    String getPhoneNumber();

    @Query("SELECT photo_uri FROM user")
    String getPhotoUri();


    @Query("SELECT * FROM user")
    UserEntity getUser();

    @Query("SELECT COUNT(*) FROM user")
    int getNumberOfUsers();

    @Query("UPDATE user SET token = null")
    void deleteToken();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserEntity userEntity);

    @Delete
    void delete(UserEntity userEntity);

    @Update
    void update(UserEntity userEntity);
}
