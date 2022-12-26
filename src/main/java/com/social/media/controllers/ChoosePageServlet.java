package com.social.media.controllers;

import com.social.media.model.Page;
import com.social.media.service.PageService;
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

public class ChoosePageServlet extends HttpServlet {
    PageService pageService = new PageService();
    EntityManager em = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            em = EntityManagerFactoryUtility.createEntityManger();
            em.getTransaction().begin();
            List<Page> pages = pageService.allPages(em);
            req.setAttribute("pages", pages);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            em.close();
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/choosePage.jsp");
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
