package com.social.media.controllers;

import com.social.media.model.Comment;
import com.social.media.model.Post;
import com.social.media.model.User;
import com.social.media.service.CommentService;

import com.social.media.util.ConnectionHelper;
import com.social.media.util.EntityManagerFactoryUtility;
import jakarta.persistence.EntityManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        EntityManager em = null;
        User user = (User) req.getSession().getAttribute("currentUser");
        String postId = req.getParameter("id");

        try {
            em = EntityManagerFactoryUtility.createEntityManger();
            em.getTransaction().begin();
            Comment comment = new Comment();
            Post post = new Post();
            comment.setPost(post);
            comment.setCommentText(req.getParameter("commentText"));
            comment.setUser(user);
            comment.getPost().setId(Integer.parseInt(postId));
            comment.setCreatedDate(new Date());
            comment.setUpdatedDate(new Date());

            if (comment.getCommentText() != null && comment.getCommentText().length() > 0) {
                commentService.addComment(em, comment);
                em.getTransaction().commit();
            } else {
                req.setAttribute("postId", postId);
                req.setAttribute("commentAdded", "please enter your comment");
            }
            wallServlet.doGet(req, resp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }
}
