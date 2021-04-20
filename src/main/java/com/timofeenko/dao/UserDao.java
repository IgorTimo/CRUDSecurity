package com.timofeenko.dao;

import com.timofeenko.model.Role;
import com.timofeenko.model.User;
import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    User getUserById(int id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(int id);

    User getUserByEmail(String email);

}
