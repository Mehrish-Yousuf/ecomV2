package com.ecomv2.shippinservice.Repository;

import com.ecomv2.shippinservice.Model.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {

}
