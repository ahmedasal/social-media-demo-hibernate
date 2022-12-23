package com.social.media.controllers;


import com.social.media.model.User;

import com.social.media.util.EntityManagerFactoryUtility;
import com.social.media.util.UnsupportedMethodException;
import jakarta.persistence.EntityManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


import static com.social.media.util.WebUtil.GET;

public class HomeServlet implements Servlet {


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
        EntityManager em = null;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        User user = null;
        try {
            em = EntityManagerFactoryUtility.createEntityManger();
            em.getTransaction().begin();
            String id = request.getParameter("id");
            if(id != null) {
               user = em.find(User.class, Integer.parseInt(id));
               request.setAttribute("user", user);
               em.getTransaction().commit();
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
