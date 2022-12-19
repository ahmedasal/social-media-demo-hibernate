package com.social.media.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "likes")
public class Like {
    @Id
    int id;
    @Column(name = "create_date")
    String createDate;
    @ManyToMany
    @JoinColumn(name = "post_id")
    Set<Post> posts = new HashSet<>();
    @ManyToMany
    @JoinColumn(name = "user")
    Set<User> users = new HashSet<>();
    public Like() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
