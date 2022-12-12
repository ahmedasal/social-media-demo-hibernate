package com.social.media.controllers;

import com.social.media.model.Comment;
import com.social.media.model.User;
import com.social.media.service.CommentService;
import com.social.media.service.WallService;
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

public class CommentServlet extends HttpServlet {
    WallServlet wallServlet = new WallServlet();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CommentService commentService = new CommentService();
        Connection connection = null;
        User user = (User) req.getSession().getAttribute("currentUser");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String date1 = formatter.format(date);
        String postId = req.getParameter("id");
        try {
            connection = ConnectionHelper.openConnection();
            Comment comment = new Comment();
            comment.setCommentText(req.getParameter("commentText"));
            comment.setUser(user);
            comment.setPostId(Integer.parseInt(postId));
            comment.setCreatedDate(date1);
            comment.setUpdatedDate(date1);

            if (comment.getCommentText() != null && comment.getCommentText().length() > 0) {
                commentService.addComment(connection, comment);
            } else {
                req.setAttribute("postId", postId );
                req.setAttribute("commentAdded", "please enter your comment");
            }
            wallServlet.doGet(req,resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
