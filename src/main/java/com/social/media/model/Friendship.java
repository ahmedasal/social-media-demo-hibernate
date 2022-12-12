package com.social.media.model;

public class Friendship {
    int id;
    int user1Id;
    int user2Id;
    String CreateDate;

    public Friendship() {
    }

    public Friendship(int user1Id, int user2Id, String createDate) {
        this.user1Id = user1Id;
        this.user2Id = user2Id;
        CreateDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(int user1Id) {
        this.user1Id = user1Id;
    }

    public int getUser2Id() {
        return user2Id;
    }

    public void setUser2Id(int user2Id) {
        this.user2Id = user2Id;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    @Override
    public String toString() {
        return "Friendship{" +
                "id=" + id +
                ", user1Id=" + user1Id +
                ", user2Id=" + user2Id +
                ", CreateDate='" + CreateDate + '\'' +
                '}';
    }
}
