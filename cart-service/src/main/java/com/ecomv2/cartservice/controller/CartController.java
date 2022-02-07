package com.ecomv2.cartservice.controller;

import com.ecomv2.cartservice.DTO.CartDTO;
import com.ecomv2.cartservice.DTO.ItemDTO;
import com.ecomv2.cartservice.domain.Cart;
import com.ecomv2.cartservice.header.HeaderGenerator;
import com.ecomv2.cartservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;


    @Autowired
    private HeaderGenerator headerGenerator;

    @GetMapping(value = "/get/{cartId}")
    public ResponseEntity<CartDTO> getCart(@PathVariable Long cartId) {
        CartDTO cart = cartService.getCart(cartId);
        if (cart != null) {
            return new ResponseEntity<CartDTO>(
                    cart,
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<CartDTO>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/addItems")
    public ResponseEntity<Cart> addItemToCart(@RequestBody CartDTO cartDto) {
        CartDTO cartDTO = null;
        Cart cart = null;
        if (cartDto.getCrtId() != null) {
            cartDTO = cartService.getCart(cartDto.getCrtId());
            if (cartDTO != null) {
                if (!cartDTO.getItemDTOList().isEmpty()) {
                    for (ItemDTO itemDTO : cartDTO.getItemDTOList()) {
                        if (cartService.checkIfItemIsExist(cartDTO.getCrtId(), itemDTO.getProductId())) {
                            cartService.changeItemQuantity(cartDTO.getCrtId(), itemDTO.getProductId(), itemDTO.getQuantity());
                        }
                    }
                }


            }
        } else {

            cartService.addItemToCart(cartDto);
            return new ResponseEntity<Cart>(
                    cart,
                    HttpStatus.CREATED);
        }
        return new ResponseEntity<Cart>(
                headerGenerator.getHeadersForError(),
                HttpStatus.BAD_REQUEST);
    }


    @DeleteMapping(value = "/removeItems", params = {"productId", "cartId"})
    public ResponseEntity<Void> removeItemFromCart(
            @RequestParam("productId") Long productId,
            @RequestParam("cartId") Long cartId) {
        CartDTO cart = cartService.getCart(cartId);
        if (cart != null) {
            cartService.deleteItemFromCart(cartId, productId);
            return new ResponseEntity<Void>(
                    headerGenerator.getHeadersForSuccessGetMethod(),
                    HttpStatus.OK);
        }
        return new ResponseEntity<Void>(
                headerGenerator.getHeadersForError(),
                HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
        return new ResponseEntity<Void>(headerGenerator.getHeadersForSuccessGetMethod(), HttpStatus.OK);
    }
}
