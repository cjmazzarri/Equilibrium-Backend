package com.equilibrium.webapp.service;

import com.equilibrium.webapp.domain.model.Payment;
import com.equilibrium.webapp.domain.repository.ClientRepository;
import com.equilibrium.webapp.domain.repository.PaymentRepository;
import com.equilibrium.webapp.domain.service.PaymentService;
import com.equilibrium.webapp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Page<Payment> getAllPaymentsByClientId(Long clientId, Pageable pageable) {
        return paymentRepository.findByClientId(clientId, pageable);
    }

    @Override
    public Payment getPaymentByIdAndClientId(Long clientId, Long paymentId) {
        return paymentRepository.findByIdAndClientId(clientId, paymentId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Payment not found with Id " + paymentId +
                                " and ClientId " + clientId));
    }

    @Override
    public Payment createPayment(Long clientId, Payment payment) {
        return clientRepository.findById(clientId).map(client -> {
            payment.setClient(client);
            return paymentRepository.save(payment);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Client", "Id", clientId));
    }

    @Override
    public Payment updatePayment(Long clientId, Long paymentId, Payment request) {
        if(!clientRepository.existsById(clientId))
            throw new ResourceNotFoundException("Client", "Id", clientId);
        return paymentRepository.findById(paymentId).map(payment -> {
            payment.setAmount(request.getAmount());
            payment.setDescription(request.getDescription());
            return paymentRepository.save(payment);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Payment", "Id", paymentId));
    }

    @Override
    public ResponseEntity<?> deletePayment(Long clientId, Long paymentId) {
        return paymentRepository.findByIdAndClientId(clientId, paymentId).map(payment -> {
            paymentRepository.delete(payment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Payment not found with Id " + paymentId + " and ClientId " + clientId));
    }
}
