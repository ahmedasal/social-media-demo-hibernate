package com.social.media.crud;

import com.social.media.model.UserPageLike;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PageLikeCrud implements Crud<UserPageLike,Integer>{
    @Override
    public UserPageLike insert(Connection connection, UserPageLike userPageLike) throws SQLException {
        Timestamp createDate = new Timestamp(new  java.util.Date().getTime());
        PreparedStatement preparedStatement = connection.prepareStatement("insert into user_page_like (user, page, create_date) values(?,?,?)");
        preparedStatement.setInt(1, userPageLike.getUserId());
        preparedStatement.setInt(2, userPageLike.getPageId());
        preparedStatement.setTimestamp(3, createDate);
        preparedStatement.execute();
        return userPageLike;
    }

    @Override
    public UserPageLike get(Connection connection, Integer key) throws SQLException {
        return null;
    }

    @Override
    public UserPageLike update(Connection connection, UserPageLike userPageLike) throws SQLException {
        return null;
    }

    @Override
    public int delete(Connection connection, Integer LikeId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from user_page_like where LikeId");
        int count = preparedStatement.executeUpdate();
        return count;
    }
}
