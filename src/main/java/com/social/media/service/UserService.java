package com.social.media.service;

import com.social.media.crud.UserCrud;
import com.social.media.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    UserCrud userCrud = new UserCrud();

    public User register(Connection connection, User user) throws SQLException {
        // TODO add user validation (add to user crud getUserByEmail return null if not exist)
        PreparedStatement preparedStatement = connection.prepareStatement("select email from users where email=? ");
        preparedStatement.setString(1, user.getEmail());
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            throw new SQLException("there is a user with the same email exists");
        } else {
            user = userCrud.insert(connection, user);
        }
        int id = getUserId(connection, user.getUsername());
        user.setId(id);
        return user;
    }

    public int getUserId(Connection connection, String username) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select id from users where username = ?");
        preparedStatement.setString(1, username);
        int id = 0;
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            id = resultSet.getInt("id");
        }
        return id;
    }

    public int getUserId(EntityManager em, String username) {
        User user = new User();
        Query q = em.createNativeQuery("select user.id from User user where user.username = :username");
        q.setParameter("username",username );
        user = (User) q.getSingleResult();
        return user.getId();
    }

    public User login(Connection connection, String username, String password) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("select id from users where username = ? and password =?");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        int id = 0;
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            id = resultSet.getInt("id");
        }
        if (id == 0) {
            return null;
        }
        User user = userCrud.get(connection, id);
        return user;
    }
}