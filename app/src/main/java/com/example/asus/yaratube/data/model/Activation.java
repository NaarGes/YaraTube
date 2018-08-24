package com.example.asus.yaratube.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Activation {

    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user_id")
    @Expose
    private int userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("files_added")
    @Expose
    private Object filesAdded;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("Fino_token")
    @Expose
    private String FinoToken;

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getFilesAdded() {
        return filesAdded;
    }

    public void setFilesAdded(Object filesAdded) {
        this.filesAdded = filesAdded;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getFinoToken() {
        return FinoToken;
    }

    public void setFinoToken(String finoToken) {
        FinoToken = finoToken;
    }
}
