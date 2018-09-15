package com.example.asus.yaratube.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("date_of_birth")
    @Expose
    private String dateOfBirth;
    @SerializedName("is_official")
    @Expose
    private boolean isOfficial;

    public String getNickname() {
        return nickname;
    }

    public String getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getAvatar() {
        return avatar;
    }
}
