package com.social.media.controllers;

import com.social.media.model.Image;
import com.social.media.service.PostService;
import com.social.media.util.ConnectionHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;


@WebServlet("/uploadServlet")
@MultipartConfig(maxFileSize = 16177215)
public class FileUploadServlet extends HttpServlet {
    PostService postService = new PostService();
    WallServlet wallServlet = new WallServlet();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        InputStream inputStream = null;
        Part filePart = req.getPart("photo");
        inputStream = filePart.getInputStream();

        Image img = new Image();
        img.setInputStream(inputStream);
        img.setPostId(Integer.parseInt(req.getParameter("id")));
        //just for debugging
        System.out.println(filePart.getName());
        System.out.println(filePart.getSize());
        System.out.println(filePart.getContentType());
        System.out.println(inputStream.available());

        try {
            connection = ConnectionHelper.openConnection();
            if (inputStream.readAllBytes().length != 0) {
                postService.saveImage(connection, img);
                req.setAttribute("upload","message has been uploaded");
                req.setAttribute("pId", img.getPostId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        wallServlet.doGet(req, resp);


    }
}
