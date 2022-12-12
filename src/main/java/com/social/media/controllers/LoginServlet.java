package com.social.media.controllers;

import com.social.media.model.User;
import com.social.media.service.UserService;
import com.social.media.util.ConnectionHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        UserService userService = new UserService();
        String username = req.getParameter("username");
        String password = req.getParameter("password");


        try {
            connection = ConnectionHelper.openConnection();
            User user = userService.login(connection, username, password);
            req.getSession().setAttribute("currentUser",user);
            resp.sendRedirect("wall");

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
