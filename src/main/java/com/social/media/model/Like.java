package com.social.media.model;

public class Like {
    int id;
    int userId;
    String createDate;
    int postId;

    public Like() {
    }

    public Like(int userId, String createDate, int postId) {

        this.userId = userId;
        this.createDate = createDate;
        this.postId = postId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
