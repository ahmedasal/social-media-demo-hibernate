package com.social.media.service;

import com.social.media.model.Post;
import com.social.media.model.Wall;
import jakarta.persistence.EntityManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class WallService {
    Wall wall = new Wall();

    //TODO i make edited
    // TODO offset and limit;
    public List<Post> getWallPosts(EntityManager em, int userId, int offset, int noOfRows) throws InterruptedException {
//        select post from Post post INNER join post.user inner join Friendship friendship on friendship.user1=post.user or " +
//        "friendship.user2 = post.user "
        System.out.println("before query"); //for debugging
        List<Post> posts = em.createQuery("select post from Post post join Friendship f ON (f.user1.id = :user and post.postOwner = f.user2.id) or (f.user2.id = :user and post.postOwner = f.user1.id) ")
                .setParameter("user", userId)
                .setFirstResult(offset)
                .setMaxResults(noOfRows)
                .getResultList();
//        Thread.sleep(5000L);
        // List<Post> editedPosts = new LinkedList<>();
        PostService postService = new PostService();
        for (Post post : posts) {
            post.setLikedByMe(postService.likedByMe(em, userId, post.getId()));
        }
        System.out.println("after query"); //for debugging
        return posts;
    }

    public int countWallPosts(EntityManager em, int userId){
        List<Post> posts = em.createQuery("select post from Post post inner join Friendship f ON (f.user1.id = :user1 and post.postOwner = f.user2.id) or (f.user2.id = :user2 and post.postOwner = f.user1.id) ").setParameter("user1", userId).setParameter("user2", userId).getResultList();
        return posts.size();
    }






}
