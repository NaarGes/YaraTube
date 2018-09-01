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
    public String getToken();

    @Query("SELECT name FROM user")
    public String getName();

    @Query("SELECT sex FROM user")
    public String getSex();

    @Query("SELECT birth_date FROM user")
    public String getBirthDate();

    @Query("SELECT phone_number FROM user")
    public String getPhoneNumber();

    @Query("SELECT * FROM user")
    public UserEntity getUser();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserEntity userEntity);

    @Query("UPDATE user SET token = null")
    void deleteToken();

    @Delete
    void delete(UserEntity userEntity);

    @Update
    void update(UserEntity userEntity);
}
