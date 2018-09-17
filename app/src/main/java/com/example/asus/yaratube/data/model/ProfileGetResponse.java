package com.example.asus.yaratube.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class ProfileGetResponse {

    @SerializedName("credit")
    @Expose
    private Credit credit;
    @SerializedName("magic_credit")
    @Expose
    private MagicCredit magicCredit;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("friends")
    @Expose
    private List<ProfileGetResponse> friends = null;
    @SerializedName("error")
    @Expose
    private String error;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("date_of_birth")
    @Expose
    private String dateOfBirth;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("email")
    @Expose
    private String email;

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public MagicCredit getMagicCredit() {
        return magicCredit;
    }

    public void setMagicCredit(MagicCredit magicCredit) {
        this.magicCredit = magicCredit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ProfileGetResponse> getFriends() {
        return friends;
    }

    public void setFriends(List<ProfileGetResponse> friends) {
        this.friends = friends;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
