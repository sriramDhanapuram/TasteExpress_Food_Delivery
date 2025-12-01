package com.tastyExpress.dao;

import java.util.List;

import com.tastyExpress.model.User;

public interface UserDAO {

    void addUser(User user);

    User getUser(int userId);

    void updateUser(User user);

    void deleteUser(int userId);

    List<User> getAllUsers();

    User login(String email, String password);
}
