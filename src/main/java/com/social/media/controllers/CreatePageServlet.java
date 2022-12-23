//package com.social.media.controllers;
//
//import com.social.media.model.Page;
//import com.social.media.model.User;
//import com.social.media.service.PageService;
//import com.social.media.util.ConnectionHelper;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//
//public class CreatePageServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/views/createPage.jsp");
//        requestDispatcher.forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Page page =  new Page();
//        Connection connection = null;
//        PageService pageService = new PageService();
//        User user = (User) req.getSession().getAttribute("currentUser");
//        page.setPageName(req.getParameter("pageName"));
//        Timestamp createDate = new Timestamp(new java.util.Date().getTime());
//        page.setCreatePageDate(createDate);
//        page.setAdminUser(user.getId());
//        try {
//            connection = ConnectionHelper.openConnection();
//            pageService.createNewPage(connection, page);
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//}
