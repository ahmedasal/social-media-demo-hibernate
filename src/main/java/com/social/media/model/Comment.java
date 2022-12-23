package com.social.media.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    int id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    Post post;
    @Column(name = "comment_text")
    String commentText;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    @Transient
    String username;
    //TODO self-Referencing
    @ManyToOne
    @JoinColumn(name = "comment_parent_id")
    Comment parentComment;
    @OneToMany(mappedBy = "parentComment")
    List<Comment> comments = new ArrayList<>();
    @Column(name = "create_date")
    Date createdDate;
    @Column(name = "update_date")
    Date updatedDate;

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Comment getParentComment() {
        return parentComment;
    }

    public void setParentComment(Comment parentComment) {
        this.parentComment = parentComment;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", post=" + post +
                ", commentText='" + commentText + '\'' +
                ", user=" + user +
                ", username='" + username + '\'' +
                ", parentComment=" + parentComment +
                ", comments=" + comments +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
