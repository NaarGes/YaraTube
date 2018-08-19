
package com.example.asus.yaratube.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel
public class CommentsSummery {

    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("count")
    @Expose
    private Integer count;

    @ParcelConstructor
    CommentsSummery() {

    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
