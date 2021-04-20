package com.timofeenko.service;

import com.timofeenko.model.Role;
import com.timofeenko.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


public interface UserService {
    List<User> getAllUsers();

    User getUserById(int id);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(int id);

    User getUserByEmail(String email);

}
