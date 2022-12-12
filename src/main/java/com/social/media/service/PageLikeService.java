package com.social.media.service;

import com.social.media.crud.PageLikeCrud;
import com.social.media.model.UserPageLike;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PageLikeService {
    PageLikeCrud pageLikeCrud = new PageLikeCrud();
    public UserPageLike addLike(Connection connection, UserPageLike userPageLike) throws SQLException {
        pageLikeCrud.insert(connection, userPageLike);
        return  userPageLike;
    }

    public int deleteLike(Connection connection, UserPageLike userPageLike) throws SQLException {
        int id = 0;
        PreparedStatement preparedStatement = connection.prepareStatement("select id from user_page_like where user = ? and page =?");
        preparedStatement.setInt(1, userPageLike.getUserId());
        preparedStatement.setInt(2, userPageLike.getPageId());
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            id = rs.getInt("id");
        }
        int count = pageLikeCrud.delete(connection, id);
        return count;
    }

    public int getLikePageId(Connection connection, UserPageLike userPageLike) throws SQLException {
        int id = 0;
        PreparedStatement preparedStatement = connection.prepareStatement("select id from user_page_like where user = ? and page =?");
        preparedStatement.setInt(1, userPageLike.getUserId());
        preparedStatement.setInt(2, userPageLike.getPageId());
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next()){
            id = rs.getInt("id");
        }
        return id;
    }

    public Boolean likedByMe(Connection connection, int userId, int pageId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select count(id) as total from user_page_like where user=? and page = ?");
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, pageId);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("total") > 0;
    }
}
