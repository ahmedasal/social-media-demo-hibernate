package com.social.media.controllers;

import com.social.media.model.Page;
import com.social.media.model.Post;
import com.social.media.model.User;
import com.social.media.service.PageService;
import com.social.media.util.ConnectionHelper;
import com.social.media.util.EntityManagerFactoryUtility;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.spec.ECField;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class PageServlet extends HttpServlet {

//    @PersistenceContext
//    EntityManager em;
    PageService pageService = new PageService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         EntityManager em = null;
        User user = (User) req.getSession().getAttribute("currentUser");
        int pageId = Integer.parseInt(req.getParameter("pageId"));

        try {
             em = EntityManagerFactoryUtility.createEntityManger();
            // em.getTransaction().begin();
            Page page = pageService.getPage(em, pageId);
            List<Post> pagePosts = pageService.showPagePosts(em, page.getId());
            System.out.println(pagePosts); //for debugging
            req.setAttribute("page", page);
            req.setAttribute("posts", pagePosts);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/page.jsp");
            requestDispatcher.forward(req, resp);
            // em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            em.close();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       EntityManager em = null;
        PageService pageService = new PageService();
        User user = (User) req.getSession().getAttribute("currentUser");
        int pageId = Integer.parseInt(req.getParameter("pageId"));
        Page page = new Page();
        Post post = new Post();
        post.setPost(req.getParameter("postText"));
        post.setPostOwner(user.getId());
        post.setPostDate(new Date());
        post.getPage().setId(pageId);


        try {
            em = EntityManagerFactoryUtility.createEntityManger();
            em.getTransaction().begin();
            if (post.getPost() != null && post.getPost().length() > 0) {
                pageService.addPostToPage(em, post);
                req.setAttribute("postAdded", "post is added successfully");
            } else {
                req.setAttribute("postAdded", "Please enter your post");
            }
            em.getTransaction().commit();
            resp.sendRedirect("page?pageId="+pageId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }


    }
}

