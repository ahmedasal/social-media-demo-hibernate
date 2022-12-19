package com.social.media.controllers;

import com.social.media.model.Image;
import com.social.media.service.PostService;
import com.social.media.util.ConnectionHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class ShowImagesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection =  null;
        PostService postService = new PostService();

        try {
            connection = ConnectionHelper.openConnection();
            Image image = postService.getImage(connection, Integer.parseInt(req.getParameter("id")));
            resp.setContentType("image/jpeg");
            resp.getOutputStream().write(image.getImg().getBinaryStream().readAllBytes());
            resp.getOutputStream().flush();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
