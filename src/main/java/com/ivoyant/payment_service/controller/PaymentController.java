package com.ivoyant.payment_service.controller;

import com.ivoyant.payment_service.entity.Payment;
import com.ivoyant.payment_service.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public Payment initiatePayment(@Valid @RequestBody Payment payment) {
        log.info("Received payment request for booking {}", payment.getBookingId());
        return paymentService.initiatePayment(payment);
    }

    @GetMapping("/{id}")
    public Payment getPayment(@PathVariable Integer id) {
        log.info("Fetching payment details for id {}", id);
        return paymentService.getPaymentById(id);
    }

    @PostMapping("/refund/{id}")
    public String refund(@PathVariable Integer id) {
        log.info("Processing refund for id {}", id);
        return paymentService.refundPayment(id);
    }

    @GetMapping("/methods")
    public List<String> getMethods() {
        log.info("Fetching all the payment methods");
        return paymentService.getPaymentMethods();
    }

    @GetMapping
    public List<Payment> getAll() {
        log.info("Fetching all the payments ");
        return paymentService.getAllPayments();
    }

}
