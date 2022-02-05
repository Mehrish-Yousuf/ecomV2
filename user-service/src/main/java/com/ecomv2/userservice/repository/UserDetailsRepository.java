package com.ecomv2.userservice.repository;

import com.ecomv2.userservice.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}
