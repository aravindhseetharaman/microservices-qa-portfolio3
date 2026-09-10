package com.lseg.paymentservice;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {
    private Map<Integer, Payment> payments = new HashMap<>();

    public PaymentService() {
        // Initialize with valid payments
        payments.put(1, new Payment(1, "GBP", "PAID", 77.33,90));
        payments.put(2, new Payment(2, "GBP", "PAID", 77.33,99));
        payments.put(3, new Payment(3, "GBP", "PAID", 77.33,88));
    }

    public Payment getPaymentById(int id) {
        return payments.get(id);
    }
}