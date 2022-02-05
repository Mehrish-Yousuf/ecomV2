package com.ecomv2.paymentservice.repository;

import com.ecomv2.paymentservice.Model.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
