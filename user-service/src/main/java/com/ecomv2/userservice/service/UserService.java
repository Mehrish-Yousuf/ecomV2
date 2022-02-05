package com.ecomv2.userservice.service;

import com.ecomv2.userservice.DTO.UserDTO;
import com.ecomv2.userservice.entity.User;

import java.util.List;


public interface UserService {
    List<User> getAllUsers();
    UserDTO getUserById(Long id);
    User getUserByName(String userName);
    User saveUser(User user);
    User updateUser(User user);
    List<User> getUserByActiveStatus(int active);
}
