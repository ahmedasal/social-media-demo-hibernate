package com.social.media.controllers;

import com.social.media.model.Page;
import com.social.media.service.PageService;
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

public class ChoosePageServlet extends HttpServlet {
    PageService pageService = new PageService();
    Connection connection = null;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            connection = ConnectionHelper.openConnection();
            Set<Page> pages = pageService.allPages(connection);
            req.setAttribute("pages", pages);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/choosePage.jsp");
        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
