package com.social.media.service;

import com.social.media.model.Image;
import com.social.media.model.Post;
import jakarta.persistence.EntityManager;

import java.util.List;

public class PostService {
    

    public Boolean likedByMe(EntityManager em, int userId, int postId) {
        List resultset = em.createQuery("select id from Like l where l.user.id= :userId and l.post.id = :postId").setParameter("userId",userId).setParameter("postId",postId).getResultList();
        return resultset.size() > 0;
    }

    public Post writePost(EntityManager em, Post post) {
        em.persist(post);
        return post;
    }

    public Post getPost(EntityManager em, Integer id) {
        Post post = em.find(Post.class, id);
        return post;

    }

    //TODO getPost (postCrud.getPost commentCrud.getPstComments
    public void deletePost(EntityManager em, int id) {
        Post post = em.find(Post.class, id);
        em.remove(post);
    }

    public List getPostComments(EntityManager em, int postId) {
        List comments = em.createQuery("select comment from Comment comment where comment.post.id = :post_id").setParameter("post_id", postId).getResultList();
        return comments.size() > 0 ? comments : null;
    }

    public Integer getLikesCount(EntityManager em, int postId) {
        List likes = em.createQuery("select post.likesCount from Post post where post.id = :post_id").setParameter("post_id", postId).getResultList();
        return likes.size() > 0 ? (Integer) likes.get(0) : null;
    }


    public String getUsername(EntityManager em, int id) {
        List users = em.createQuery("select user.username from User user where user.id = :id").setParameter("id", id).getResultList();
        return users.size() > 0 ? (String) users.get(0) : null;
    }

    public Image saveImage(EntityManager em, Image img) {
        em.persist(img);
        return img;
    }

    public Image getImage(EntityManager em, int imageId) {
       return em.find(Image.class, imageId);
    }

    public List<Image> getPostImagesIds(EntityManager em, int postId) {
        List<Image> images = em.createQuery("select image.id from Image image where image.post.id = :postId").setParameter("id",postId).getResultList();
        return images;
    }

}
