package com.social.media.service;

import com.social.media.crud.PostCrud;
import com.social.media.model.Comment;
import com.social.media.model.Image;
import com.social.media.model.Post;
import com.social.media.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PostService {
    PostCrud postCrud = new PostCrud();

    public Boolean likedByMe(Connection connection, int userId, int postId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select count(id) as total from likes where user=? and post_id = ?");
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, postId);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("total") > 0;
    }

    public Post writePost(Connection connection, Post post) throws SQLException {
        postCrud.insert(connection, post);
        return post;
    }

    public Post getPost(Connection connection, Integer id) throws SQLException {
        Post post = postCrud.get(connection, id);
        return post;

    }

    //TODO getPost (postCrud.getPost commentCrud.getPstComments
    public int deletePost(Connection connection, int id) throws SQLException {
        int numberOfRowsDeleted = postCrud.delete(connection, id);
        return numberOfRowsDeleted;
    }

    public ArrayList<Comment> getPostComments(Connection connection, int postId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select * from comments where post_id = ? order by create_date");
        preparedStatement.setInt(1, postId);
        ArrayList<Comment> comments = new ArrayList<>();

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Comment comment = new Comment();
            User user = new User();
            comment.setId(resultSet.getInt("id"));
            comment.setPostId(postId);
            comment.setCommentText(resultSet.getString("comment_text"));
            user.setId(resultSet.getInt("user_id"));
            comment.setUser(user);
            comment.setUsername(getUsername(connection, user.getId()));
            comment.setParentCommentId(resultSet.getInt("comment_parent_id"));
            comment.setCreatedDate(resultSet.getString("create_date"));
            comment.setUpdatedDate(resultSet.getString("update_date"));
            comments.add(comment);
        }

        return comments;
    }

    public int getLikesCount(Connection connection, int postId) throws SQLException {
        int count = 0;
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT likes_count FROM socialMediaApp.posts where id= ?;");
        preparedStatement.setInt(1, postId);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            count = rs.getInt("likes_count");
        }
        return count;
    }


    public String getUsername(Connection connection, int id) throws SQLException {
        String username = null;
        PreparedStatement preparedStatement = connection.prepareStatement("select username from users where id = ?;");
        preparedStatement.setInt(1, id);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            username = rs.getString("username");
        }
        return username;
    }

    public Image saveImage(Connection connection, Image img) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into post_images (post_id, image) values(?,?)");
        preparedStatement.setInt(1, img.getPost().getId());
        preparedStatement.setBlob(2, img.getImg());
        preparedStatement.execute();
        return img;
    }

    public Image getImage(Connection connection, int imageId) throws SQLException {
        Image image = new Image();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from post_images where id = ?");
        preparedStatement.setInt(1, imageId);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            image.setId(imageId);
            image.getPost().setId(rs.getInt("post_id"));
            image.setImg(rs.getBlob("image"));
        }

        return image;
    }

    public List<Image> getPostImagesIds(Connection connection, int postId) throws SQLException {
        List<Image> images = new LinkedList<>();

        PreparedStatement preparedStatement = connection.prepareStatement("select id from post_images where post_id = ?");
        preparedStatement.setInt(1, postId);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Image image = new Image();
            image.setId(rs.getInt("id"));
           images.add(image);
        }
        return images;
    }

}
