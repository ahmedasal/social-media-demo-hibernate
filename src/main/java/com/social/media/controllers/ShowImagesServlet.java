package com.social.media.controllers;

import com.social.media.model.Image;
import com.social.media.service.PostService;
import com.social.media.util.ConnectionHelper;
import com.social.media.util.EntityManagerFactoryUtility;
import jakarta.persistence.EntityManager;

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
        EntityManager em =  null;
        PostService postService = new PostService();

        try {
            em = EntityManagerFactoryUtility.createEntityManger();
            em.getTransaction().begin();
            Image image = postService.getImage(em, Integer.parseInt(req.getParameter("id")));
            resp.setContentType("image/jpeg");
            resp.getOutputStream().write(image.getImg().getBinaryStream().readAllBytes());
            resp.getOutputStream().flush();
            em.getTransaction().commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            em.close();
        }

    }
}
