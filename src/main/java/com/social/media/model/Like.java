package com.social.media.model;

import jakarta.persistence.*;
@Entity
@Table(name = "likes")
public class Like {
    @Id
    int id;
    @Column(name = "create_date")
    String createDate;
    @ManyToOne
    @JoinColumn(name = "post_id")
    Post post;

    @ManyToOne
    @JoinColumn(name = "user")
    User user;

}
