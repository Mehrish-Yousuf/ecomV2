package com.ecomv2.cartservice.repository;

import com.ecomv2.cartservice.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository  extends JpaRepository<Cart, Long> {
}
