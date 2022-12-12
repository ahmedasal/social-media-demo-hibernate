package com.social.media.crud;

import com.social.media.model.Comment;
import com.social.media.model.User;


import java.sql.*;

public class CommentCrud implements Crud<Comment, Integer> {

    //create
    @Override
    public Comment insert(Connection connection, Comment comment) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into comments (comment_text, user_id,comment_parent_id, create_date, update_date , post_id) values (?,?,?,?,?,?)");
        preparedStatement.setString(1, comment.getCommentText());
        preparedStatement.setInt(2, comment.getUser().getId());
        if (comment.getParentCommentId() != null)
            preparedStatement.setInt(3, comment.getParentCommentId());
        else
            preparedStatement.setNull(3, Types.NULL);
        preparedStatement.setString(4, comment.getCreatedDate());
        preparedStatement.setString(5, comment.getUpdatedDate());
        preparedStatement.setInt(6, comment.getPostId());
        preparedStatement.execute();
        return comment;
    }


    //read
    @Override
    public Comment get(Connection connection, Integer id) throws SQLException {
        Comment comment = new Comment();
        User user = new User();

        PreparedStatement preparedStatement = connection.prepareStatement("select * from comments where id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            comment.setCommentText(resultSet.getString("comment_text"));
            user.setId(resultSet.getInt("user_id"));
            comment.setParentCommentId(resultSet.getInt("comment_parent_id"));
            comment.setCreatedDate(resultSet.getString("create_date"));
            comment.setUpdatedDate(resultSet.getString("update_date"));
            comment.setPostId(resultSet.getInt("post_id"));

        }
        comment.setId(id);
        comment.setUser(user);
        resultSet.close();
        preparedStatement.close();
        return comment;
    }

    //update
    @Override
    public Comment update(Connection connection, Comment comment) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update comments set comment_text = ?, user_id =? , comment_parent_id =?, create_date =?, update_date=? , post_id = ? where id =?");
        preparedStatement.setString(1, comment.getCommentText());
        preparedStatement.setInt(2, comment.getUser().getId());

        if (comment.getParentCommentId() != null)
            preparedStatement.setInt(3, comment.getParentCommentId());
        else
            preparedStatement.setNull(3, Types.NULL);

        preparedStatement.setString(4, comment.getCreatedDate());
        preparedStatement.setString(5, comment.getUpdatedDate());
        preparedStatement.setInt(6, comment.getPostId());

        preparedStatement.setInt(7, comment.getId());
        preparedStatement.execute();
        preparedStatement.close();

        return comment;
    }


    //delete
    @Override
    public int delete(Connection connection, Integer id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from comments where id = ? ");
        preparedStatement.setInt(1, id);

        int count = preparedStatement.executeUpdate();

        return count;
    }


}
