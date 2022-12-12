package com.social.media.service;

import com.social.media.crud.CommentCrud;
import com.social.media.model.Comment;
import com.social.media.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentService {
    CommentCrud commentCrud = new CommentCrud();

    // add comment
    public Comment addComment(Connection connection, Comment comment) throws SQLException {
        commentCrud.insert(connection, comment);
        // then>> update posts set comments_count=comments_count+1 where id=?
        PreparedStatement preparedStatement = connection.prepareStatement("update posts set comments_count = comments_count+1 where id = ?");
        preparedStatement.setInt(1, comment.getPostId());
        preparedStatement.execute();
        preparedStatement.close();
        return comment;
    }

    //remove comment
    public int removeComment(Connection connection, int id) throws SQLException {
        int postId = 0;
        PreparedStatement preparedStatement = connection.prepareStatement("select post_id from comments where id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            postId = resultSet.getInt("post_id");
        }
        commentCrud.delete(connection, id);
        // decrease comments by one
        preparedStatement = connection.prepareStatement("update posts set comments_count = comments_count-1 where id = ?");
        preparedStatement.setInt(1, postId);
        int count = preparedStatement.executeUpdate();
        preparedStatement.close();
        return count;
    }


    public ArrayList<Comment> getCommentchildren(Connection connection, int commentId) throws SQLException {
        ArrayList<Comment> comments = new ArrayList<>();
        Comment comment = new Comment();
        PreparedStatement preparedStatement = connection.prepareStatement("select c.comment_text , c.id, p.id as parent_id, c.user_id  ,p.comment_text , c.create_date , c.update_date from comments p , comments c  where c.comment_parent_id = p.id and p.id = 26 ;");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            User user = new User();
            comment.setId(resultSet.getInt("id"));
            comment.setCommentText(resultSet.getString("comment_text"));
            user.setId(resultSet.getInt("user_id"));
            comment.setUser(user);
            comment.setParentCommentId(resultSet.getInt("parent_id"));
            comment.setCreatedDate(resultSet.getString("create_date"));
            comment.setUpdatedDate(resultSet.getString("update_date"));


            comments.add(comment);

        }


        return comments;
    }
}





