package com.social.media.crud;

import com.social.media.model.User;

import java.sql.*;

public class UserCrud implements Crud<User, Integer> {


    //create
    @Override
    public User insert(Connection connection, User user) throws SQLException {


        PreparedStatement preparedStatement = connection.prepareStatement("insert into users( firstName, secondName, birthday,username,email,password) values (?,?,?,?,?,?);");

        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getSecondName());
        preparedStatement.setString(3, user.getBirthday());
        preparedStatement.setString(4, user.getUsername());
        preparedStatement.setString(5, user.getEmail());
        preparedStatement.setString(6, user.getPassword());


        preparedStatement.execute();

//        ResultSet resultSet = preparedStatement.executeQuery("select id from users");
//        while (resultSet.next()) {
//            user.setId(resultSet.getInt("id"));
//        }
//
//        preparedStatement.execute();
        preparedStatement.close();


        return user;
    }


    //read
    @Override
    public User get(Connection connection, Integer key) throws SQLException {
        User user = new User();

        PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id=?");

        preparedStatement.setInt(1, key);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            user.setFirstName(resultSet.getString("firstName"));
            user.setSecondName(resultSet.getString("secondName"));
            user.setBirthday(resultSet.getString("birthday"));
            user.setUsername(resultSet.getString("username"));
            user.setEmail(resultSet.getString("email"));
            user.setId(resultSet.getInt("id"));
            user.setPassword(resultSet.getString("password"));
        }

        resultSet.close();
        preparedStatement.close();

        return user;
    }


    //delete
    //update
    @Override
    public User update(Connection connection, User user) throws SQLException {


        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET username = ?, firstName = ?,  secondName = ?, birthday = ?, email = ?, `password` = ?  WHERE (id = ?); ");


        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getFirstName());
        preparedStatement.setString(3, user.getSecondName());
        preparedStatement.setString(4, user.getBirthday());
        preparedStatement.setString(5, user.getEmail());
        preparedStatement.setString(6, user.getPassword());
        preparedStatement.setInt(7, user.getId());
        preparedStatement.execute();


        return user;
    }

    @Override
    public int delete(Connection connection, Integer id) throws SQLException {

        PreparedStatement preparedStatement = connection.prepareStatement("Delete from users where id=?");
        preparedStatement.setInt(1, id);
        int count = preparedStatement.executeUpdate();
        preparedStatement.close();


        return count;

    }


}





