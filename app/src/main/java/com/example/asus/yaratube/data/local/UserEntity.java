package com.example.asus.yaratube.data.local;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.net.Uri;

@Entity(tableName = "user")
public class UserEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String token = null;

    private String nickname;

    private String name;

    private String sex;

    @ColumnInfo(name = "birth_date")
    private String birthDate;

    @ColumnInfo(name = "phone_number")
    private String phoneNumber;

    private String email;

    @ColumnInfo(name = "google_photo_url")
    private String googlePhotoUrl;

    @ColumnInfo(name = "photo_uri")
    private String photoUri;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGooglePhotoUrl() {
        return googlePhotoUrl;
    }

    public void setGooglePhotoUrl(String googlePhotoUrl) {
        this.googlePhotoUrl = googlePhotoUrl;
    }

    public String  getPhotoUri() {
        return photoUri;
    }

    public void setPhotoUri(String photoUri) {
        this.photoUri = photoUri;
    }
}
