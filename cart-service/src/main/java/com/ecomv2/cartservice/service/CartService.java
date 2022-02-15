package com.ecomv2.cartservice.service;

import com.ecomv2.cartservice.DTO.CartDTO;
import com.ecomv2.cartservice.DTO.ItemDTO;
import com.ecomv2.cartservice.domain.Item;


public interface CartService {

    public void addItemToCart(CartDTO cart);

    public CartDTO getCart(Long cartId);

    public void changeItemQuantity(Long cartId, Long productId, Integer quantity);

    public void deleteItemFromCart(Long cartId, Long productId);

    public boolean checkIfItemIsExist(Long cartId, Long productId);

    //public List<Item> getAllItemsFromCart(Long cartId);

    public void deleteCart(Long cartId);

    Item addItemToExistingCart(ItemDTO itemDTO, Long cartId);
}
