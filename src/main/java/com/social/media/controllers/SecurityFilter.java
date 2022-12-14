package com.social.media.controllers;

import com.social.media.model.User;
import com.social.media.service.UserService;
import com.social.media.util.EntityManagerFactoryUtility;
import jakarta.persistence.EntityManager;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {


    public static final String LOGIN_PATH = "/login";
    public static final String REGISTER_PATH = "/register";
    public static final String STATIC_RESOURCE_PREFIX = "/static";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        UserService userService = new UserService();

        EntityManager em = null;
        String path = request.getServletPath();
        System.out.println(request.getServletPath());

        if (path.equals(LOGIN_PATH) || path.startsWith(STATIC_RESOURCE_PREFIX) || path.equals(REGISTER_PATH)) {
            filterChain.doFilter(request, response);
            return;
        }


        User user = (User) request.getSession().getAttribute("currentUser");
        if (user != null) {
            filterChain.doFilter(request, response);
        } else {
//            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//            System.out.println(response.getStatus());
//            response.sendRedirect("/social/login");
            try {
                em = EntityManagerFactoryUtility.createEntityManger();
                user = userService.getUserByUsername(em, "ahmedasal1");
                request.getSession().setAttribute("currentUser", user);
            }finally {
                em.close();
            }


        }

    }

    @Override
    public void destroy() {

    }
}
