package com.social.media.service;

import com.social.media.model.Like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeService {
    // add like
    public Like likePost(Connection connection, Like like) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM likes where user =? and  post_id=?");
        preparedStatement.setInt(1, like.getUserId());
        preparedStatement.setInt(2, like.getPostId());
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next() == false) {
            preparedStatement = connection.prepareStatement("insert into likes(user, create_date,post_id) values (?,?,?)");
            preparedStatement.setInt(1, like.getUserId());
            preparedStatement.setString(2, like.getCreateDate());
            preparedStatement.setInt(3, like.getPostId());
            preparedStatement.execute();

            //TODO increase for like count by one
            preparedStatement = connection.prepareStatement("update posts set likes_count = likes_count+1 where  id = ?");
            preparedStatement.setInt(1, like.getPostId());
            preparedStatement.execute();
        }

        preparedStatement.close();
        return like;
    }

    public int getLikeId(Connection connection, int userId, int postId) throws SQLException {
        int likeId = 0;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM likes where user =? and  post_id=?");
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, postId);
        ResultSet rs = preparedStatement.executeQuery();

        if (rs.next()) {
            likeId = rs.getInt("id");
        }


        return likeId;
    }


    public int deleteLike(Connection connection, int likeId) throws SQLException {
        int postId =0;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT post_id from likes where id = ?");
        preparedStatement.setInt(1, likeId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()) {
            postId = resultSet.getInt("post_id");
        }
        preparedStatement = connection.prepareStatement("delete from likes where id =? ;");
        preparedStatement.setInt(1, likeId);
        int count = preparedStatement.executeUpdate();
        System.out.println(postId);
        //TODO decrease likes count by one
        preparedStatement = connection.prepareStatement("update posts set likes_count = likes_count-1 where  id = ?");
        preparedStatement.setInt(1, postId);
        preparedStatement.execute();

        preparedStatement.close();

        return count;
    }



}
