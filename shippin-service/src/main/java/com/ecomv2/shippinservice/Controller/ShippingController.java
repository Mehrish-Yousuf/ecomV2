package com.ecomv2.shippinservice.Controller;

import com.ecomv2.shippinservice.DTO.OrderDTO;
import com.ecomv2.shippinservice.DTO.ShippingDTO;
import com.ecomv2.shippinservice.DTO.UserDTO;
import com.ecomv2.shippinservice.FeignCleints.OrderClient;
import com.ecomv2.shippinservice.FeignCleints.UserClient;
import com.ecomv2.shippinservice.Model.Shipping;
import com.ecomv2.shippinservice.ShippingService.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ship")
public class ShippingController {

    @Autowired
    private UserClient userClient;

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private ShippingService shippingService;

    @PostMapping(value="/order")
    ResponseEntity<Shipping> shipOrder(@RequestBody ShippingDTO shippingDTO){
        Shipping shipping = new Shipping();
        UserDTO user = userClient.getUserById(shippingDTO.getUserId());

        OrderDTO order = orderClient.getOrderById(shippingDTO.getOrderId());
        shipping.setOrderId(order.getId());
        shipping.setUserId(user.getId());
        shipping.setContact(shippingDTO.getContact());
        shipping.setAddress(shippingDTO.getAddress());
        shipping.setEmail(shippingDTO.getEmail());
        shipping.setShippingStatus("INITIATED");
        shipping.setShippingAmount(shippingDTO.getShippingAmount());

        shipping = shippingService.save(shipping);

        return new ResponseEntity<Shipping>(shipping, HttpStatus.CREATED);







    }
}
