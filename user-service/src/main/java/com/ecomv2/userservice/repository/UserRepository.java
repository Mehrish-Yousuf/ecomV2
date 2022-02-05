package com.ecomv2.userservice.repository;

import com.ecomv2.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserName(String userName);
    List<User> findByActive(int active);
}
