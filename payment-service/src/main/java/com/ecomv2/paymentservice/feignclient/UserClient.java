package com.ecomv2.paymentservice.feignclient;

import com.ecomv2.paymentservice.DTO.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "User", url = "http://localhost:4003/")
public interface UserClient {

    @GetMapping(value = "user/getById/{id}")
    public UserDTO getUserById(@PathVariable("id") Long id);
}
