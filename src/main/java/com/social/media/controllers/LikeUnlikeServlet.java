package com.social.media.controllers;

import com.social.media.model.Like;
import com.social.media.model.User;
import com.social.media.service.LikeService;
import com.social.media.util.ConnectionHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LikeUnlikeServlet extends HttpServlet {
    WallServlet wallServlet = new WallServlet();
    PageServlet pageServlet = new PageServlet();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        User user = (User) req.getSession().getAttribute("currentUser");
        LikeService likeService = new LikeService();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String date1 = formatter.format(date);
        String postId = req.getParameter("id");
        int postId1 = Integer.parseInt(postId);

        try {
            connection = ConnectionHelper.openConnection();
            String operation = req.getParameter("operation");
            switch (operation) {
                case "like":
                    Like like = new Like();
                    like.setPostId(postId1);
                    like.setCreateDate(date1);
                    like.setUserId(user.getId());
                    likeService.likePost(connection, like);
                    break;
                case "unlike":
                    int likeId = 0;
                    likeId = likeService.getLikeId(connection, user.getId(), Integer.parseInt(postId));
                    if (likeId != 0) {
                        likeService.deleteLike(connection, likeId);
                    }
                    break;
            }


            String page = req.getParameter("page");

            if (page != null)
                pageServlet.doGet(req, resp);
            else
                wallServlet.doGet(req, resp);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
