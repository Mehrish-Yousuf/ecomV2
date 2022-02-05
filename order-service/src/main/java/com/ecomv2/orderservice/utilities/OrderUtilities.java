package com.ecomv2.orderservice.utilities;

import com.ecomv2.orderservice.DTO.CartDTO;
import com.ecomv2.orderservice.DTO.ItemDTO;

import java.math.BigDecimal;
import java.util.Set;


public class OrderUtilities {

    public static BigDecimal countTotalPrice(CartDTO cart) {
        BigDecimal total = BigDecimal.ZERO;
        Set<ItemDTO> items = cart.getItemDTOList();
        for(ItemDTO itemDTO : items){
            total = total.add(itemDTO.getSubTotal());
        }
        return total;
    }
}
