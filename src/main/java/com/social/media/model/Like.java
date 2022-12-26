package com.social.media.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "likes")
public class Like {
    @Id
    int id;
    @Column(name = "create_date")
    Date createDate;
    @ManyToOne
    @JoinColumn(name = "post_id")
    Post post;

    @ManyToOne
    @JoinColumn(name = "user")
    User user;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", createDate='" + createDate + '\'' +
                ", post=" + post +
                ", user=" + user +
                '}';
    }
}
