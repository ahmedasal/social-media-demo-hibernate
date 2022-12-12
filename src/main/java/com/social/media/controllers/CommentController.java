package com.social.media.controllers;

import com.social.media.model.Image;
import com.social.media.service.PostService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

public class CommentController {


    public static void main(String[] args) throws SQLException, IOException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/socialMediaApp", "root", "test123");
        PostService postService = new PostService();
        Image image = postService.getImage(connection, 8);
        byte[] bytes= image.getInputStream().readAllBytes();
        FileOutputStream fileOutputStream = new FileOutputStream("/home/ahmed/Desktop/happy/");
        fileOutputStream.write(bytes);
        System.out.println(Arrays.toString(bytes));
        connection.close();


    }

}
