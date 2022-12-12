package com.social.media.crud;

import com.social.media.model.Post;

import java.sql.*;

public class PostCrud implements Crud<Post, Integer> {


    @Override
    public Post insert(Connection connection, Post post) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("insert into posts (post, postDate, updateDate, postOwner,page_id) " +
                        "values (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        Timestamp createdOn = new Timestamp(new java.util.Date().getTime());
        preparedStatement.setString(1, post.getPost());
        preparedStatement.setTimestamp(2, createdOn);
        preparedStatement.setTimestamp(3, createdOn);
        preparedStatement.setInt(4, post.getPostOwner());
        preparedStatement.setInt(5, post.getPageId());
        preparedStatement.executeUpdate();

        ResultSet generatedKeysResultSet = preparedStatement.getGeneratedKeys();
        generatedKeysResultSet.next();
        post.setId(generatedKeysResultSet.getInt(1));

        preparedStatement.close();
        return post;
    }


    @Override
    public Post get(Connection connection, Integer id) throws SQLException {
        Post post = new Post();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from posts where id = ?");
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            post.setId(id);
            post.setPost(resultSet.getString("post"));
            post.setPostOwner(resultSet.getInt("postOwner"));
            post.setPostDate(resultSet.getTimestamp("postDate"));


        }
        resultSet.close();
        preparedStatement.close();
        return post;
    }

    @Override
    public Post update(Connection connection, Post post) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("update posts set post = ?, postOwner = ?, updateDate=? where id = ?");

        preparedStatement.setString(1, post.getPost());
        preparedStatement.setInt(2, post.getPostOwner());
        preparedStatement.setTimestamp(3, new Timestamp(new java.util.Date().getTime()));
        preparedStatement.setInt(4, post.getId());


        preparedStatement.execute();
        preparedStatement.close();
        return post;
    }

    @Override
    public int delete(Connection connection, Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("Delete from posts where id=?");

        preparedStatement.setInt(1, id);
        int count = preparedStatement.executeUpdate();
        preparedStatement.close();
        return count;
    }

}
