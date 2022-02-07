package com.ecomv2.paymentservice.ServiceImpl;

import com.ecomv2.paymentservice.DTO.OrderDTO;
import com.ecomv2.paymentservice.DTO.PaymentDTO;
import com.ecomv2.paymentservice.DTO.UserDTO;
import com.ecomv2.paymentservice.Model.Payment;
import com.ecomv2.paymentservice.Service.PaymentService;
import com.ecomv2.paymentservice.feignclient.OrderClient;
import com.ecomv2.paymentservice.feignclient.UserClient;
import com.ecomv2.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentServiceImpl  implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private UserClient userClient;

    @Override
    public Payment savePayment(PaymentDTO paymentDTO) throws Exception {
        Payment payment = new Payment();
        UserDTO user = userClient.getUserById(paymentDTO.getUserId());
        OrderDTO order = orderClient.getOrderById(paymentDTO.getOrderId());
        payment.setUserId(user.getId());
        payment.setOrderId(order.getId());
        payment.setPaymentMode(paymentDTO.getPaymentMode());
        if(order.getTotal().compareTo(BigDecimal.valueOf(paymentDTO.getAmount()))==0){
            payment.setTotalAmount(paymentDTO.getAmount());
        }
        else {
            throw new Exception("The amount is not correct");
        }

        return paymentRepository.save(payment);
    }
}
