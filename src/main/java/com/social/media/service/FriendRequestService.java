//package com.social.media.service;
//
//import com.social.media.model.FriendRequest;
//import com.social.media.model.Friendship;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class FriendRequestService {
//
//
//    // send friend request
//    public FriendRequest sendFriendRequest(Connection connection, FriendRequest friendRequest) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement("insert into friendship_request (user1, user2, create_date) values  (?,?,?)");
//        preparedStatement.setInt(1, friendRequest.getSenderUser());
//        preparedStatement.setInt(2,friendRequest.getReceiverUser());
//        preparedStatement.setString(3, friendRequest.getCreateDate());
//
//        preparedStatement.execute();
//        return friendRequest;
//    }
//
//    // cancel friend request
//    public int cancelRequest(Connection connection, FriendRequest friendRequest) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement("delete from friendship_request where (user1 = ? and user2 =? ) or (user2 = ? and user1 =? )   ");
//
//        preparedStatement.setInt(1, friendRequest.getReceiverUser());
//        preparedStatement.setInt(2, friendRequest.getSenderUser());
//        preparedStatement.setInt(3, friendRequest.getReceiverUser());
//        preparedStatement.setInt(4, friendRequest.getSenderUser());
//
//
//        int count = preparedStatement.executeUpdate();
//        return count;
//
//
//
//    }
//}
