package com.social.media.service;

import com.social.media.model.Post;
import com.social.media.model.Wall;
import jakarta.persistence.EntityManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class WallService {
    Wall wall = new Wall();

    // TODO offset and limit;
    public List<Post> getWallPosts(EntityManager em, int userId, int offset, int noOfRows)  {
//        select post from Post post INNER join post.user inner join Friendship friendship on friendship.user1=post.user or " +
//        "friendship.user2 = post.user "
        System.out.println("before query");
        List posts = em.createQuery("select post from Post post inner join Friendship f ON (f.user1.id = :user1 and post.postOwner = f.user2.id) or (f.user2.id = :user2 and post.postOwner = f.user1.id) ").setParameter("user1",userId).setParameter("user2",userId).getResultList();
        System.out.println("after query");
        return posts;
    }

//    public int countWallPosts(Connection connection, int userId, int offset, int noOfRows) throws SQLException {
//        int count = 0;
//        PreparedStatement preparedStatement = connection.prepareStatement("select count(id) as co from posts " +
//                "where postOwner in (select IF(user1=?,user2, user1) as friend from friendship  where user2=? or user1=?)  " +
//                "or  0 < (select count(id) from user_page_like where user = ? and page = page_id) " +
//                "order by updateDate desc ");
//        preparedStatement.setInt(1, userId);
//        preparedStatement.setInt(2, userId);
//        preparedStatement.setInt(3, userId);
//        preparedStatement.setInt(4, userId);
//
//
//        ResultSet rs = preparedStatement.executeQuery();
//        if (rs.next()) {
//            count =rs.getInt("co");
//        }
//        return count;
//    }
//
//    //


}
