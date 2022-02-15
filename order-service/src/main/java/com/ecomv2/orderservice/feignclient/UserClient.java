package com.ecomv2.orderservice.feignclient;

import com.ecomv2.orderservice.DTO.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//@FeignClient(name = "User", url = "http://localhost:4003/")
@FeignClient(name = "User", url = "http://localhost:2002/")
public interface UserClient {

    @GetMapping(value = "user/getById/{id}")
    public UserDTO getUserById(@PathVariable("id") Long id);
}
