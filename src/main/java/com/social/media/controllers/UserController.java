package com.social.media.controllers;

import com.social.media.model.User;
import com.social.media.service.UserService;

public class UserController {
    static UserService userService = new UserService();
    public static void register() {
        // open connection
        // create user object
        // userservice.register
        // close connection
    }

    public static void login() {
        // open connection
        // create user object
        // userservice.login
        // close connection
    }

    public static void main(String[] args) {
        register();
        login();
    }
}
