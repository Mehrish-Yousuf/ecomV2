package com.ecomv2.orderservice.controller;

import com.ecomv2.orderservice.DTO.CartDTO;
import com.ecomv2.orderservice.DTO.OrderDTO;
import com.ecomv2.orderservice.DTO.UserDTO;
import com.ecomv2.orderservice.domain.Order;
import com.ecomv2.orderservice.feignclient.CartClient;
import com.ecomv2.orderservice.feignclient.UserClient;
import com.ecomv2.orderservice.header.HeaderGenerator;
import com.ecomv2.orderservice.service.OrderService;
import com.ecomv2.orderservice.utilities.OrderUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private UserClient userClient;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartClient cartClient;

    @Autowired
    private HeaderGenerator headerGenerator;


    @PostMapping(value = "/save")
    public ResponseEntity<Order> saveOrder(
            @RequestParam("userId") Long userId,
            @RequestParam("cartId") Long cartId,
            HttpServletRequest request) {

       CartDTO cart = cartClient.getCart(cartId);
        UserDTO user = userClient.getUserById(userId);
        if (cart != null && user != null) {
            Order order = this.createOrder(cart, user.getId());
            try {
                orderService.saveOrder(order);
                cartClient.deleteCart(cartId);
                return new ResponseEntity<Order>(
                        order,
                        headerGenerator.getHeadersForSuccessPostMethod(request, order.getId()),
                        HttpStatus.CREATED);
            } catch (Exception ex) {
                ex.printStackTrace();
                return new ResponseEntity<Order>(
                        headerGenerator.getHeadersForError(),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Order>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND);
    }

    @GetMapping (value = "/getbyId/{id}")
    public ResponseEntity<OrderDTO> getOneProductById(@PathVariable ("id") long id){
        OrderDTO order =  orderService.getOrderDetails(id);
        if(order != null) {
            return new ResponseEntity<OrderDTO>(
                    order,
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<OrderDTO>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND);
    }

    @PostMapping (value = "/cancel/{id}")
    public ResponseEntity<Boolean> cancelOrder(@PathVariable ("id") long id){
        OrderDTO order =  orderService.getOrderDetails(id);
        if(order!=null){
            order.setStatus("CANCELLED");
            //orderService.saveOrder(order);
        }


        if(order != null) {
            return new ResponseEntity<Boolean>(
                    true,
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND);
    }


    private Order createOrder(CartDTO cart, Long userId) {
        Order order = new Order();
        order.setCartId(cart.getCrtId());
        order.setUserId(userId);
        order.setTotal(OrderUtilities.countTotalPrice(cart));
        order.setOrderedDate(LocalDate.now());
        order.setStatus("PAYMENT_EXPECTED");
        return order;
    }
}
