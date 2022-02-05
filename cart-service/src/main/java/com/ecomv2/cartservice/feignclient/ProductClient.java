package com.ecomv2.cartservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name = "product-catalog", url = "http://localhost:4000/")
public interface ProductClient {

//    @GetMapping(value = "catalog/productById/{id}")
//    public Product getProductById(@PathVariable(value = "id") Long productId);

}
