package com.social.media.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String username;
    String firstName;
    String secondName;
    String password;
    String birthday;
    String email;
    @OneToMany
    List<Post> posts = new ArrayList<>();
    @OneToMany(mappedBy = "user")
    List<Comment> comments = new ArrayList<>();
    @ManyToMany(mappedBy = "adminUsers")
    Set<Page> pages =new HashSet<>();
    @OneToMany(mappedBy = "user")
    Set<Like> likes;

    public User() {
    }
    public void addComment(Comment comment){
        this.comments.add(comment);
        comment.setUser(this);
    }
    public void removeComment(Comment comment){
        this.comments.remove(comment);
    }

    public User(int id, String username, String firstName, String secondName, String password, String birthday, String email) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthday = birthday;
        this.email = email;
        this.password = password;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String lastname) {
        this.secondName = lastname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Set<Page> getPages() {
        return pages;
    }

    public void setPages(Set<Page> pages) {
        this.pages = pages;
    }

    public Set<Like> getLikes() {
        return likes;
    }

    public void setLikes(Set<Like> likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", password='" + password + '\'' +
                ", birthday='" + birthday + '\'' +
                ", email='" + email + '\'' +




                '}';
    }
}
