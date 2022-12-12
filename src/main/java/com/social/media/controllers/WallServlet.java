package com.social.media.controllers;


import com.social.media.model.Image;
import com.social.media.model.Post;
import com.social.media.model.User;
import com.social.media.service.PostService;
import com.social.media.service.WallService;
import com.social.media.util.ConnectionHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

public class WallServlet extends HttpServlet {

    WallService wallService = new WallService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 1;
        int noOfPages = 0;
        int noOfRows = 5;
        Connection connection = null;
        User user = (User) req.getSession().getAttribute("currentUser");
        if (req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));
        try {
            connection = ConnectionHelper.openConnection();
            Set<Post> posts = wallService.getWallPosts(connection, user.getId(), (page - 1) * noOfRows, noOfRows);
            req.setAttribute("posts", posts);
            req.setAttribute("currentPage", page);
            int count = wallService.countWallPosts(connection, user.getId(), (page - 1) * noOfRows, noOfRows);
            if (count / noOfRows == 0) {
                noOfPages = count / noOfRows;
            } else {
                noOfPages = count / noOfRows + 1;
            }
            req.setAttribute("lastPage", noOfPages);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/home.jsp");
            requestDispatcher.forward(req, resp);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection connection = null;
        PostService postService = new PostService();

        try {
            connection = ConnectionHelper.openConnection();
            PostRequestReader.PostWithPhotos postWithPhotos = PostRequestReader.readPostRequest(req, resp);
            if (postWithPhotos.post != null && postWithPhotos.post.getPost().length() > 0) {
                postService.writePost(connection, postWithPhotos.post);

//                req.setAttribute("postAdded", "post is added successfully");

                for(Image image : postWithPhotos.images) {
                    image.setPostId(postWithPhotos.post.getId());
                    postService.saveImage(connection, image);
                }

            } else {
                req.setAttribute("postAdded", "Please enter your post");
            }

            doGet(req, resp);


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
