package com.ecomv2.orderservice.feignclient;


import com.ecomv2.orderservice.DTO.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cart-service", url = "http://localhost:4001/")
public interface CartClient {

    @GetMapping(value = "cart/get/{cartId}")
    public CartDTO getCart(@PathVariable Long cartId);

    @DeleteMapping(value="cart/delete/{cartId}")
    public void deleteCart(@PathVariable Long cartId);



}

