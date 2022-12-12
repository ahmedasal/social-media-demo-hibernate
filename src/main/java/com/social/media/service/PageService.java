package com.social.media.service;

import com.social.media.crud.PageCrud;
import com.social.media.model.Page;
import com.social.media.model.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PageService {

    PageCrud pageCrud = new PageCrud();

    public Page getPage(Connection connection, int id) throws SQLException {
        return pageCrud.get(connection, id);
    }

    //create new page.
    public Page createNewPage(Connection connection, Page page) throws SQLException {
        pageCrud.insert(connection, page);
        return page;
    }

    //delete page.
    public int deletePage(Connection connection, int id) throws SQLException {
        int count = pageCrud.delete(connection, id);
        return count;
    }

    //add post to page.
    public Post addPostToPage(Connection connection, Post post) throws SQLException {
        PostService postService = new PostService();
        postService.writePost(connection, post);
        return post;
    }

    //show all page posts.
    public List<Post> showPagePosts(Connection connection, int pageId, int userId) throws SQLException {
        List<Post> posts = new LinkedList<>();
        PostService postService = new PostService();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from posts where page_id = ? order by postDate desc ");
        preparedStatement.setInt(1, pageId);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {

            Post post = Post.fromDatabase(resultSet);

            post.setComments(postService.getPostComments(connection, post.getId()));
            post.setLikesCount(postService.getLikesCount(connection, post.getId()));
            post.setLikedByMe(postService.likedByMe(connection, userId, post.getId()));
            post.setUsername(postService.getUsername(connection, post.getPostOwner()));
            posts.add(post);
        }
        return posts;
    }

    public Set<String> allPageNames(Connection connection) throws SQLException {
        Set<String> names = new LinkedHashSet<>();
        PreparedStatement preparedStatement = connection.prepareStatement("select name from pages");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            names.add(name);
        }
        return names;
    }

    public Set<Page> allPages(Connection connection) throws SQLException {
        Set<Page> pages = new LinkedHashSet<>();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from pages");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            pages.add(new Page(id, name));
        }
        return pages;
    }

//    public int getPageId(Connection connection, String name) throws SQLException {
//        int id = 0;
//        PreparedStatement preparedStatement = connection.prepareStatement("select id from pages where name = ?");
//        preparedStatement.setString(1, name);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        if (resultSet.next()) {
//            id = resultSet.getInt("id");
//        }
//        return id;
//    }

    public int userCreator(Connection connection, String name) throws SQLException {
        int userCreator = 0;
        PreparedStatement preparedStatement = connection.prepareStatement("select user_creator from pages where name = ?  ");
        preparedStatement.setString(1, name);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            userCreator = rs.getInt("user_creator");
        }
        return userCreator;
    }



}
