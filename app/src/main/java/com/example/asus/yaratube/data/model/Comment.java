package com.example.asus.yaratube.data.model;


import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("comment_text")
    @Expose
    private String commentText;
    @SerializedName("likes")
    @Expose
    private Integer likes;
    @SerializedName("dislikes")
    @Expose
    private Integer dislikes;
    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("date_added")
    @Expose
    private String dateAdded;
    @SerializedName("comment_reply")
    @Expose
    private List<Object> commentReply = null;
    @SerializedName("user_avatar")
    @Expose
    private String userAvatar;
    @SerializedName("product")
    @Expose
    private Integer product;
    @SerializedName("is_read")
    @Expose
    private Boolean isRead;
    @SerializedName("is_approved")
    @Expose
    private Boolean isApproved;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("date_modify")
    @Expose
    private String dateModify;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public List<Object> getCommentReply() {
        return commentReply;
    }

    public void setCommentReply(List<Object> commentReply) {
        this.commentReply = commentReply;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public Integer getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDateModify() {
        return dateModify;
    }

    public void setDateModify(String dateModify) {
        this.dateModify = dateModify;
    }

}
