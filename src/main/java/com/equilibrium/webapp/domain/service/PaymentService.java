package com.equilibrium.webapp.domain.service;

import com.equilibrium.webapp.domain.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface PaymentService {
    Page<Payment> getAllPaymentsByCommerceIdAndClientId(Long commerceId, Long clientId, Pageable pageable);
    Payment getPaymentByCommerceIdAndClientIdAndId(Long commerceId, Long clientId, Long paymentId);
    Payment getLastPaymentByCommerceIdAndClientId(Long commerceId, Long clientId);
    Payment createPayment(Long commerceId, Long clientId, Payment payment);
    Payment updatePayment(Long commerceId, Long clientId, Long paymentId, Payment request);
    ResponseEntity<?> deletePayment(Long commerceId, Long clientId, Long paymentId);
}
