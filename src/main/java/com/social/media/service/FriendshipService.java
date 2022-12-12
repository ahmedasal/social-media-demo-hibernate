package com.social.media.service;

import com.social.media.model.Friendship;
import com.social.media.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FriendshipService {

    // get all friends to specific user
    public ArrayList<User> getAllFriends(Connection connection, int userId) throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = connection.prepareStatement("select user2,username, firstName, secondName, birthday, email  from friendship f, users u where user2 = id and user1 = ? ");
        preparedStatement.setInt(1, userId);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("user2"));
            user.setUsername(resultSet.getString("username"));
            user.setFirstname(resultSet.getString("firstName"));
            user.setLastname(resultSet.getString("secondName"));
            user.setBirthday(resultSet.getString("birthday"));
            user.setEmail(resultSet.getString("email"));

            users.add(user);


        }

        return users;


    }


    // unfriend

    public int unfriend(Connection connection, Friendship friendship) throws SQLException {

       // TODO how to add or user2 and user1
        PreparedStatement preparedStatement = connection.prepareStatement("delete from friendship where (user1 = ? and user2 =? ) or (user2 = ? and user1 =? )   ");

        preparedStatement.setInt(1, friendship.getUser1Id());
        preparedStatement.setInt(2, friendship.getUser2Id());
        preparedStatement.setInt(3, friendship.getUser1Id());
        preparedStatement.setInt(4, friendship.getUser2Id());

        int count = preparedStatement.executeUpdate();
        return count;


    }


    // confirm friendship
    public Friendship confirmFriendship(Connection connection, Friendship friendship) throws SQLException{
        int friendRequestId = 0;
        PreparedStatement preparedStatement = connection.prepareStatement("select id from friendship_request where user1 =? and user2 =? ");
        preparedStatement.setInt(1, friendship.getUser1Id());
        preparedStatement.setInt(2,friendship.getUser2Id());
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            friendRequestId = rs.getInt("id");

        }


        preparedStatement = connection.prepareStatement("insert into friendship (user1, user2, create_time,friend_request_id) values  (?,?,?,?)");
        preparedStatement.setInt(1, friendship.getUser1Id());
        preparedStatement.setInt(2,friendship.getUser2Id());
        preparedStatement.setString(3, friendship.getCreateDate());
        preparedStatement.setInt(4,friendRequestId);

        preparedStatement.execute();

        preparedStatement = connection.prepareStatement("update friendship_request set confirm_status =1 , confirm_date=? where id = ?");
        preparedStatement.setString(1, friendship.getCreateDate());
        preparedStatement.setInt(2, friendRequestId);


        preparedStatement.execute();



        return friendship;
    }

}
