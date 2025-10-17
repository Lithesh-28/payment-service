package com.ivoyant.payment_service.service;

import com.ivoyant.payment_service.entity.Invoice;
import com.ivoyant.payment_service.entity.Payment;
import com.ivoyant.payment_service.exception.PaymentNotFoundException;
import com.ivoyant.payment_service.repository.InvoiceRepository;
import com.ivoyant.payment_service.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Transactional
    public Payment initiatePayment(Payment payment) {
        payment.setDate(LocalDateTime.now());

        // Mock condition to fail payment
        if (payment.getAmount() <= 0 || payment.getBookingId() == null) {
            payment.setStatus("FAILED");
            paymentRepository.save(payment);
            log.warn("Payment failed for booking {}", payment.getBookingId());
            return payment;
        }

        // Mock success condition
        payment.setStatus("SUCCESS");
        Payment savedPayment = paymentRepository.save(payment);
        log.info("Payment initiated for booking {}", payment.getBookingId());

        // Generate mock invoice only if payment is successful
        Invoice invoice = Invoice.builder()
                .bookingId(savedPayment.getBookingId())
                .invoicePdfUrl("http://mock-invoice.com/invoice-" + savedPayment.getId() + ".pdf")
                .build();
        invoiceRepository.save(invoice);

        return savedPayment;
    }


    public Payment getPaymentById(Integer id) {
        return paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + id));
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public String refundPayment(Integer id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + id));
        payment.setStatus("REFUNDED");
        paymentRepository.save(payment);
        log.info("Refund processed for payment {}", id);
        return "Refund successful for payment " + id;
    }

    public List<String> getPaymentMethods() {
        return List.of("CREDIT_CARD", "DEBIT_CARD", "UPI", "NET_BANKING");
    }
}

