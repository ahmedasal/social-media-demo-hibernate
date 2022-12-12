package com.social.media.controllers;

import com.social.media.crud.UserCrud;
import com.social.media.model.User;
import com.social.media.service.UserService;
import com.social.media.util.ConnectionHelper;
import com.social.media.util.UnsupportedMethodException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

import static com.social.media.util.WebUtil.GET;

public class HomeServlet implements Servlet {

    UserCrud userCrud = new UserCrud();
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

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        User user = null;
        try {
            Connection connection = ConnectionHelper.openConnection();
            String id = request.getParameter("id");
            if(id != null) {
               user = userCrud.get(connection, Integer.parseInt(id));
               request.setAttribute("user", user);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        switch (request.getMethod()){
            case GET :

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/display-user.jsp");
                requestDispatcher.forward(request, response);

            default:
                throw new RuntimeException(new UnsupportedMethodException(request.getMethod()));


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
