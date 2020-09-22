package org.example.db;

import org.example.User;

import java.util.List;

public interface UserDao {
    void insert(User user);
    void insertStatics(User user);
    List<User> loadAllCustomer();

}
