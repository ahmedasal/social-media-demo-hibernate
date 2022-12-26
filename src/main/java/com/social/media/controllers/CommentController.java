package com.social.media.controllers;


import com.social.media.model.User;
import com.social.media.service.UserService;
import com.social.media.service.WallService;
import com.social.media.util.EntityManagerFactoryUtility;
import jakarta.persistence.EntityManager;

import java.sql.SQLException;
import java.util.List;

public class CommentController {

    public static void main(String[] args) throws Exception {
        EntityManager em = EntityManagerFactoryUtility.createEntityManger();
        WallService wallService = new WallService();
        List posts = wallService.getWallPosts(em,45,5,10);
        System.out.println(posts.size());

        System.out.println(posts);
    }



}
