package com.social.media.model;


import jakarta.persistence.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String post;
    private Date postDate;
    private int postOwner;
    @Column(name = "likes_count")
    private int likesCount;
    @Column(name = "comments_count")
    private int commentsCount;
    @ManyToOne
    @JoinColumn(name = "page_id")
    private Page page;
    //TODO lazy load

    // TODO Hibernate annotation
    @OneToMany(mappedBy = "post") // , fetch = FetchType.EAGER)
    List<Comment> comments;
    @Transient
    String username;
    @Transient
    boolean likedByMe;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    List<Image> images;

    public Post() {

    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }


    public void addImage(Image image) {
        this.images.add(image);
        image.setPost(this);
    }

    public void removeImage(Image image) {
        this.images.remove(image);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setPost(this);
    }

    public void removeComment(Comment comment) {
        this.comments.remove(comment);
    }


    public Post(String post, Date postDate, int postOwner) {
        this.post = post;
        this.postDate = postDate;
        this.postOwner = postOwner;
    }

    public static Post fromDatabase(ResultSet resultSet) throws SQLException {
        Post post = new Post();

        post.setId(resultSet.getInt("id"));
        post.setPostDate(resultSet.getDate("postDate"));
        post.setPostOwner(resultSet.getInt("postOwner"));
        post.setPost(resultSet.getString("post"));
        return post;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public int getPostOwner() {
        return postOwner;
    }

    public void setPostOwner(int postOwner) {
        this.postOwner = postOwner;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public boolean isLikedByMe() {
        return likedByMe;
    }

    public boolean getLikedByMe() {
        return likedByMe;
    }

    public void setLikedByMe(boolean likedByMe) {
        this.likedByMe = likedByMe;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", post='" + post + '\'' +
                ", postDate=" + postDate +
                ", postOwner=" + postOwner +
                ", likesCount=" + likesCount +
                ", commentsCount=" + commentsCount +
                ", page=" + page +
                '}';
    }
}
