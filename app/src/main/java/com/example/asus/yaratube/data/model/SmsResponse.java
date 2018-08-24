package com.example.asus.yaratube.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SmsResponse {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("error")
    @Expose
    private Integer error;
    @SerializedName("nickname")
    @Expose
    private String nickname;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
