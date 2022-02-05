package com.ecomv2.paymentservice.ServiceImpl;

import com.ecomv2.paymentservice.Model.Payment;
import com.ecomv2.paymentservice.Service.PaymentService;
import com.ecomv2.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl  implements PaymentService {

    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }
}
