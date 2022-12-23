package com.social.media.model;

import jakarta.persistence.*;

import java.sql.Blob;
import java.sql.SQLException;

@Entity
@Table(name = "post_images")
public class Image {
    @Id
    int id;
    @ManyToOne
    @JoinColumn(name = "post_id")
    Post post;
    // TODO is this best practice
    @Lob
    @Column(name = "image")
    Blob img;



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

    public Blob getImg() throws SQLException {
        return img;
    }

    public void setImg(Blob img) {
        this.img = img;
    }
}
