package com.social.media.model;


import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "pages")
public class Page {
    @Id
    int id;
    @Column(name = "name")
    String pageName;
    @Column(name = "create_date")
    Timestamp createPageDate;
    @ManyToMany
    @JoinTable(name = "page_admin", joinColumns = {@JoinColumn(name = "page_id")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    Set<User> adminUsers = new HashSet<>();
    @OneToMany(mappedBy = "page")
    List<Post> posts = new ArrayList<>();

    public Page(int id, String pageName) {
        this.id = id;
        this.pageName = pageName;
    }

    public void addPost(Post post){
        this.posts.add(post);
        post.setPage(this);
    }

    //TODO is this method is correct here I try to delete post from post list
    public void removePost(Post post){
        this.posts.remove(post);
    }


    public void addUser(User user){
        this.adminUsers.add(user);
        user.getPages().add(this);
    }
    public void removeUser(User user){
        this.adminUsers.remove(user);
        user.getPages().remove(this);
    }


    public Page() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public Timestamp getCreatePageDate() {
        createPageDate = new Timestamp(new java.util.Date().getTime());
        return createPageDate;
    }

    public void setCreatePageDate(Timestamp createPageDate) {
        this.createPageDate = createPageDate;
    }

    public Set<User> getAdminUsers() {
        return adminUsers;
    }

    public void setAdminUsers(Set<User> adminUsers) {
        this.adminUsers = adminUsers;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "Page{" +
                "id=" + id +
                ", pageName='" + pageName + '\'' +
                ", createPageDate=" + createPageDate +
                '}';
    }
}
