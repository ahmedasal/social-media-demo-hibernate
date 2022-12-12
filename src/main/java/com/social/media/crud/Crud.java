package com.social.media.crud;

import com.social.media.model.Post;
import com.social.media.model.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface Crud<T, K> {

    T insert(Connection connection, T t) throws SQLException;

    T get(Connection connection, K key) throws SQLException;

    T update(Connection connection, T t) throws SQLException;

    int delete(Connection connection, K k) throws SQLException;



    //update

}
