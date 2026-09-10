package com.lseg.paymentservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @GetMapping("/{id}")
        public ResponseEntity<Payment> getPayment(@PathVariable int id) {
            Payment payment = paymentService.getPaymentById(id);
            if (payment == null) {
                return ResponseEntity.notFound().build();  // Returns 404
            }
            return ResponseEntity.ok(payment);  // Returns 200 + payment data
        }
    }
