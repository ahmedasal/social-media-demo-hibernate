package com.social.media.service;

import com.social.media.model.Like;
import com.social.media.model.Post;
import com.social.media.model.User;
import jakarta.persistence.EntityManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LikeService {
    // add like
    public Like likePost(EntityManager em, Like like) throws SQLException {
        if (getLikeId(em, like.getUser().getId(), like.getPost().getId()) == 0) {
            em.persist(like);
            //TODO increase for like count by one
            Post post = em.find(Post.class, like.getPost().getId());
            post.setLikesCount(post.getLikesCount() + 1);
        }
        return like;
    }


    public int getLikeId(EntityManager em, int userId, int postId) {
        List<Integer> likes = em.createQuery("select l.id from Like l where l.post.id = :postId and l.user.id = :userId").setParameter("postId", postId).setParameter("userId", userId).getResultList();
        if (likes.size() != 0) {
            int likeId = likes.get(0);
            return likeId;
        } else
            return 0;
    }


    public void deleteLike(EntityManager em, int likeId) throws SQLException {
        Like like = em.find(Like.class, likeId);
        em.remove(like);
        //TODO decrease likes count by one
        Post post = em.find(Post.class, like.getPost().getId());
        post.setLikesCount(post.getLikesCount() - 1);
    }


}
