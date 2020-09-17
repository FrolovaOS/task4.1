package org.example.db;

import org.example.User;

import java.util.List;

public interface UserDao {
    void insert(User user);
    void insert1(User user);
    List<User> loadAllCustomer();

}
