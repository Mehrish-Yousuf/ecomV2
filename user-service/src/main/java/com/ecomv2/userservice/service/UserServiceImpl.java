package com.ecomv2.userservice.service;

import com.ecomv2.userservice.DTO.UserDTO;
import com.ecomv2.userservice.entity.User;
import com.ecomv2.userservice.entity.UserRole;
import com.ecomv2.userservice.repository.UserRepository;
import com.ecomv2.userservice.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserDTO getUserById(Long id) {
        UserDTO userDTO = new UserDTO();
        Optional<User> userOpt = userRepository.findById(id);
        User user = userOpt.get();
        if(user!=null){
            userDTO.setId(user.getId());
            userDTO.setUserName(user.getUserName());
            userDTO.setUserPassword(user.getUserPassword());
            userDTO.setActive(user.getActive());

        }
        return userDTO;
    }

    @Override
    public User getUserByName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User saveUser(User user) {
        user.setActive(1);
        UserRole role = userRoleRepository.findUserRoleByRoleName("ROLE_USER");
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user){
        user.setActive(1);
        UserRole role = userRoleRepository.findUserRoleByRoleName("ROLE_USER");
        user.setRole(role);
        return userRepository.save(user);
    }

    @Override
    public List<User> getUserByActiveStatus(int active) {
        List<User> users = userRepository.findByActive(active);
        return users;
    }
}
