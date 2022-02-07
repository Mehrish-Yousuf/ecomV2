package com.ecomv2.paymentservice.Service;

import com.ecomv2.paymentservice.DTO.PaymentDTO;
import com.ecomv2.paymentservice.Model.Payment;

public interface PaymentService {
    public Payment savePayment(PaymentDTO payment) throws Exception;


}
