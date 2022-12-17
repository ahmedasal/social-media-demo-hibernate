package com.social.media.controllers;


import com.social.media.model.User;
import com.social.media.service.UserService;
import com.social.media.util.EntityManagerFactoryUtility;
import jakarta.persistence.EntityManager;

public class CommentController {

    public static void main(String[] args) {
        EntityManager em = EntityManagerFactoryUtility.createEntityManger();
        UserService userService = new UserService();


        System.out.println(userService.getUserId(em,"ahmedasal1"));


    }



}
