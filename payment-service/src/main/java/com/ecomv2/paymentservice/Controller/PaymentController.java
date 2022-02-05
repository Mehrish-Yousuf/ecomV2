package com.ecomv2.paymentservice.Controller;

import com.ecomv2.paymentservice.DTO.OrderDTO;
import com.ecomv2.paymentservice.DTO.PaymentDTO;
import com.ecomv2.paymentservice.DTO.UserDTO;
import com.ecomv2.paymentservice.Model.Payment;
import com.ecomv2.paymentservice.Service.PaymentService;
import com.ecomv2.paymentservice.feignclient.OrderClient;
import com.ecomv2.paymentservice.feignclient.UserClient;
import com.ecomv2.paymentservice.header.HeaderGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    PaymentService paymentService;

    @Autowired
    HeaderGenerator headerGenerator;



    @PostMapping("/add")
    ResponseEntity<Payment>  savePayment(@RequestBody PaymentDTO paymentDTO, HttpServletRequest request) {

        Payment payment = new Payment();
        UserDTO user = userClient.getUserById(paymentDTO.getUserId());
        OrderDTO order = orderClient.getOrderById(paymentDTO.getOrderId());
        payment.setUserId(user.getId());
        payment.setOrderId(order.getId());
        payment.setPaymentMode(paymentDTO.getPaymentMode());
        payment.setTotalAmount(paymentDTO.getAmount());

        try {
            paymentService.savePayment(payment);

            return new ResponseEntity<Payment>(
                    payment,
                    headerGenerator.getHeadersForSuccessPostMethod(request, payment.getId()),
                    HttpStatus.CREATED);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<Payment>(
                    headerGenerator.getHeadersForError(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

