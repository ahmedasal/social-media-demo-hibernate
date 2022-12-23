package com.social.media.service;

import com.social.media.model.User;
import jakarta.persistence.EntityManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    public User register(EntityManager em, User user) throws SQLException {
        // TODO add user validation (add to user crud getUserByEmail return null if not exist)
        List resultSet = em.createQuery("select user from User user where user.email = :email").setParameter("email", user.getEmail()).getResultList();
        if (resultSet.size() > 0) {
            throw new SQLException("there is a user with the same email exists");
        } else {
            em.persist(user);
        }
        return user;
    }

    public User getUserByUsername(EntityManager em, String username) {
        List resultList = em.createQuery("select u from User u where u.username = :username")
                .setParameter("username", username)
                .getResultList();
        return resultList.size() > 0 ? (User) resultList.get(0) : null;
    }

    public Integer getUserId(EntityManager em, String username) {
        List resultList = em.createNativeQuery("select id from users where username = :username")
                .setParameter("username", username)
                .getResultList();
        return resultList.size() > 0 ? (Integer) resultList.get(0) : null;
    }

    public User login(EntityManager em, String username, String password) throws SQLException {
        User user = null;
        List resultset = em.createQuery("select user from User user where user.username = :username and user.password = :password").setParameter("username", username).setParameter("password", password).getResultList();
        if (resultset.size() > 0) {
            user = (User) resultset.get(0);
        } else {
            return null;
        }
        return user;
    }
}