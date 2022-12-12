package com.social.media.crud;



import com.social.media.model.Friendship;
import com.social.media.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FriendshipCrud implements Crud<Friendship, Integer>{

    @Override
    public Friendship insert(Connection connection, Friendship friendship) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into friendship (user1 , user2, create_time) values (?,?,?)");
        preparedStatement.setInt(1, friendship.getUser1Id());
        preparedStatement.setInt(2, friendship.getUser2Id());
        preparedStatement.setString(3,friendship.getCreateDate());

        preparedStatement.execute();


        return friendship;
    }

    @Override
    public Friendship get(Connection connection, Integer friendshipId) throws SQLException {
       Friendship friendship = new Friendship();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from friendship where friendship_id =?");
        preparedStatement.setInt(1, friendshipId);

        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
          friendship.setUser1Id(resultSet.getInt("user1"));
          friendship.setUser2Id(resultSet.getInt("user2"));
          friendship.setCreateDate(resultSet.getString("create_time"));
        }
        friendship.setId(friendshipId);
        return friendship;
    }

    @Override
    public Friendship update(Connection connection, Friendship friendship) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update friendship set user1=? , user2 = ? , create_time = ? where friendship_id = ?");

        preparedStatement.setInt(1, friendship.getUser1Id());
        preparedStatement.setInt(2, friendship.getUser2Id());
        preparedStatement.setString(3, friendship.getCreateDate());

        preparedStatement.setInt(4, friendship.getId());
        preparedStatement.execute();
        return friendship;


    }

    @Override
    public int delete(Connection connection, Integer friendshipId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from friendship where friendship_id =?");
        preparedStatement.setInt(1, friendshipId);

        int count = preparedStatement.executeUpdate();
        return count;

    }
}
