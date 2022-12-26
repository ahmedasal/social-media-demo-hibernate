package com.social.media.controllers;


import com.social.media.model.Image;
import com.social.media.model.Post;
import com.social.media.model.User;
import com.social.media.service.PostService;
import com.social.media.service.WallService;
import com.social.media.util.ConnectionHelper;
import com.social.media.util.EntityManagerFactoryUtility;
import jakarta.persistence.EntityManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class WallServlet extends HttpServlet {

    WallService wallService = new WallService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = null;
        int page = 1;
        int noOfPages = 0;
        int noOfRows = 5;
        User user = (User) req.getSession().getAttribute("currentUser");
        if (req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));
        try {
            em = EntityManagerFactoryUtility.createEntityManger();
            em.getTransaction().begin();
            List<Post> posts = wallService.getWallPosts(em, user.getId(), (page - 1) * noOfRows, noOfRows);
            req.setAttribute("posts", posts);
            req.setAttribute("currentPage", page);
            int count = wallService.countWallPosts(em, user.getId());
            if (count % noOfRows == 0) {
                noOfPages = count / noOfRows;
            } else {
                noOfPages = count / noOfRows + 1;
            }
            req.setAttribute("lastPage", noOfPages);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/home.jsp");
            requestDispatcher.forward(req, resp);
            em.getTransaction().commit();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManager em = null;
        PostRequestReader.PostWithPhotos postWithPhotos = null;
        PostService postService = new PostService();
        try {
            em = EntityManagerFactoryUtility.createEntityManger();
            em.getTransaction().begin();
            postWithPhotos = PostRequestReader.readPostRequest(req, resp);
            if (postWithPhotos.post != null && postWithPhotos.post.getPost().length() > 0) {
                postService.writePost(em, postWithPhotos.post);
                req.setAttribute("postAdded", "post is added successfully");
                for (Image image : postWithPhotos.images) {
                    Post post = new Post();
                    image.setPost(post);
                    image.getPost().setId(postWithPhotos.post.getId());
                    postService.saveImage(em, image);
                }
                em.getTransaction().commit();
            } else {
                req.setAttribute("postAdded", "Please enter your post");
            }
            doGet(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }
}
