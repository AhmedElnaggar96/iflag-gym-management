package com.iflag.service;

import com.iflag.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    List<User> findActiveUsers();

    User saveUser(User user);

    User findUserById(Long id);

    User findUserByEmail(String email);

    long countUsers();

    void deactivateUser(Long id);

    void activateUser(Long id);

    void resetPassword(Long id,  String newPassword);

}
