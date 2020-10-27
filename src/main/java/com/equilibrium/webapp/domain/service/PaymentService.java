package com.equilibrium.webapp.domain.service;

import com.equilibrium.webapp.domain.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PaymentService {
    Page<Payment> getAllPaymentsByClientId(Long clientId, Pageable pageable);
    Payment getPaymentByIdAndClientId(Long clientId, Long paymentId);
    Payment createPayment(Long clientId, Payment payment);
    Payment updatePayment(Long clientId, Long paymentId, Payment request);
    ResponseEntity<?> deletePayment(Long clientId, Long paymentId);
}
