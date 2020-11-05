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
    public Page<Payment> getAllPaymentsByCommerceIdAndClientId(Long commerceId, Long clientId, Pageable pageable) {
        this.validateClient(clientId, commerceId);
        return paymentRepository.findByClientId(clientId, pageable);
    }

    @Override
    public Payment getPaymentByCommerceIdAndClientIdAndId(Long commerceId, Long clientId, Long paymentId) {
        this.validateClient(clientId, commerceId);
        return paymentRepository.findByIdAndClientId(paymentId, clientId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Payment not found with Id " + paymentId +
                                " and ClientId " + clientId));
    }

    @Override
    public Payment createPayment(Long commerceId, Long clientId, Payment payment) {
        this.validateClient(clientId, commerceId);
        return clientRepository.findById(clientId).map(client -> {
            payment.setClient(client);
            if (payment.getAmount() > client.getCreditAmount()){
                throw new IllegalArgumentException("Payment amount must not be greater than" +
                        " the Client's credit amount.");
            }
            client.setCreditAmount(client.getCreditAmount() - payment.getAmount());
            return paymentRepository.save(payment);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Client not found with Id " + clientId));
    }

    @Override
    public Payment updatePayment(Long commerceId, Long clientId, Long paymentId, Payment request) {
        this.validateClient(clientId, commerceId);
        return paymentRepository.findByIdAndClientId(paymentId, clientId).map(payment -> {
            payment.setAmount(request.getAmount());
            payment.setDescription(request.getDescription());
            return paymentRepository.save(payment);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Payment", "Id", paymentId));
    }

    @Override
    public ResponseEntity<?> deletePayment(Long commerceId, Long clientId, Long paymentId) {
        this.validateClient(clientId, commerceId);
        return paymentRepository.findByIdAndClientId(paymentId, clientId).map(payment -> {
            paymentRepository.delete(payment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Payment not found with Id " + paymentId + " and ClientId " + clientId));
    }

    public void validateClient(Long clientId, Long commerceId){
        if(!clientRepository.existsByIdAndCommerceId(clientId, commerceId)){
            throw new ResourceNotFoundException(
                    "Client not found with Id " + clientId +
                            " and CommerceId " + commerceId);}
    }
}
