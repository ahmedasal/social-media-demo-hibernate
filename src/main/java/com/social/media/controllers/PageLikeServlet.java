//package com.social.media.controllers;
//
//import com.social.media.model.Like;
//import com.social.media.model.User;
//import com.social.media.model.UserPageLike;
//
//import com.social.media.util.ConnectionHelper;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//public class PageLikeServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Connection connection = null;
//        User user = (User) req.getSession().getAttribute("currentUser");
//        PageLikeService pageLikeService = new PageLikeService();
//
//        int pageId = Integer.parseInt(req.getParameter("id"));
//        UserPageLike userPageLike = new UserPageLike();
//        userPageLike.setPageId(pageId);
//        userPageLike.setUserId(user.getId());
//        try {
//            connection = ConnectionHelper.openConnection();
//            String operation = req.getParameter("operation");
//            switch (operation) {
//                case "like":
//                    pageLikeService.addLike(connection, userPageLike);
//                    break;
//                case "unlike":
//                    int likeId = 0;
//                    likeId = pageLikeService.getLikePageId(connection, userPageLike);
//                    if (likeId != 0) {
//                        pageLikeService.deleteLike(connection, userPageLike);
//                    }
//                    break;
//            }
//
//            resp.sendRedirect("page?pageId="+pageId);
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } finally {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//    }
//}
