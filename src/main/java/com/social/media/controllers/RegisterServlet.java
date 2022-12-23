package com.social.media.controllers;

import com.social.media.model.User;
import com.social.media.service.UserService;
import com.social.media.util.ConnectionHelper;
import com.social.media.util.EntityManagerFactoryUtility;
import com.social.media.util.UnsupportedMethodException;
import jakarta.persistence.EntityManager;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static com.social.media.util.WebUtil.GET;
import static com.social.media.util.WebUtil.POST;

public class RegisterServlet implements Servlet {

    UserService userService = new UserService();
    ServletConfig servletConfig;
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
    }
    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        EntityManager em = null;

        try {
            switch (request.getMethod()) {
                case GET:

                    RequestDispatcher dispatcher = request.getRequestDispatcher("/views/register.jsp");
                    dispatcher.forward(request, response);
                    break;
                case POST:
                    User user = new User();

                    user.setUsername( request.getParameter("username") );
                    user.setFirstName(request.getParameter("firstname"));
                    user.setSecondName(request.getParameter("lastname"));
                    user.setPassword(request.getParameter("password"));
                    user.setBirthday(request.getParameter("birthday"));
                    user.setEmail(request.getParameter("email"));

                    em = EntityManagerFactoryUtility.createEntityManger();
                    user = userService.register(em, user);

                    response.getWriter().println("You are welcome, your id is " +user.getId());
                    break;
                default:
                    throw new UnsupportedMethodException(request.getMethod());
            }
        } catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println(ex.getMessage());
        }finally {
            if(em != null) {
                try {
                    em.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }

}
