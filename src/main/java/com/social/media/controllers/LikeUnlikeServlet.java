package com.social.media.controllers;

import com.social.media.model.Like;
import com.social.media.model.Post;
import com.social.media.model.User;
import com.social.media.service.LikeService;
import com.social.media.util.ConnectionHelper;
import com.social.media.util.EntityManagerFactoryUtility;
import jakarta.persistence.EntityManager;
import org.hibernate.event.spi.EntityCopyObserverFactory;

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
//    PageServlet pageServlet = new PageServlet();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = null;
        User user = (User) req.getSession().getAttribute("currentUser");
        LikeService likeService = new LikeService();
        int postId = Integer.parseInt(req.getParameter("id"));
        try {
            em = EntityManagerFactoryUtility.createEntityManger();
            em.getTransaction().begin();
            String operation = req.getParameter("operation");
            switch (operation) {
                case "like":
                    Like like = new Like();
                    Post post = new Post();
                    post.setId(postId);
                    like.setPost(post);
                    like.setCreateDate(new Date());
                    like.setUser(user);
                    likeService.likePost(em, like);
                    break;
                case "unlike":
                    int likeId = 0;
                    likeId = likeService.getLikeId(em, user.getId(), postId);
                    if (likeId != 0) {
                        likeService.deleteLike(em, likeId);
                    }
                    break;
            }
            em.getTransaction().commit();

            String page = req.getParameter("page");

//            if (page != null)
//                pageServlet.doGet(req, resp);
//            else
                wallServlet.doGet(req, resp);


        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }

    }
}
