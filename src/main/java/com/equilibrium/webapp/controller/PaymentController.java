package com.equilibrium.webapp.controller;

import com.equilibrium.webapp.domain.model.Payment;
import com.equilibrium.webapp.domain.service.PaymentService;
import com.equilibrium.webapp.resource.PaymentResource;
import com.equilibrium.webapp.resource.SavePaymentResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Payments", description = "Payments API")
@RestController
@RequestMapping("/api/commerces/{commerceId}/clients/{clientId}")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/payments")
    public Page<PaymentResource> getAllPaymentsByCommerceIdAndClientId(
            @PathVariable(name = "commerceId") Long commerceId,
            @PathVariable(name = "clientId") Long clientId, Pageable pageable){
        Page<Payment> paymentPage = paymentService.getAllPaymentsByCommerceIdAndClientId(commerceId, clientId, pageable);
        List<PaymentResource> resources = paymentPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/payments/{paymentId}")
    public PaymentResource getPaymentByCommerceIdAndClientIdAndId(
            @PathVariable(name = "commerceId") Long commerceId,
            @PathVariable(name = "clientId") Long clientId,
            @PathVariable(name = "paymentId") Long paymentId){
        return convertToResource(paymentService.getPaymentByCommerceIdAndClientIdAndId(commerceId, clientId, paymentId));
    }

    @GetMapping("/payments/latest")
    public PaymentResource getLastPaymentByCommerceIdAndClientId(
            @PathVariable(name = "commerceId") Long commerceId,
            @PathVariable(name = "clientId") Long clientId){
        return convertToResource(paymentService.getLastPaymentByCommerceIdAndClientId(commerceId, clientId));
    }

    @PostMapping("/payments")
    public PaymentResource createPayment(
            @PathVariable(name = "commerceId") Long commerceId,
            @PathVariable(name = "clientId") Long clientId,
            @Valid @RequestBody SavePaymentResource resource){
        return convertToResource(paymentService.createPayment(commerceId, clientId, convertToEntity(resource)));
    }

    @PutMapping("/payments/{paymentId}")
    public PaymentResource updatePayment(
            @PathVariable(name = "commerceId") Long commerceId,
            @PathVariable(name = "clientId") Long clientId,
            @PathVariable(name = "paymentId") Long paymentId,
            @Valid @RequestBody SavePaymentResource resource){
        return convertToResource(paymentService.updatePayment(commerceId, clientId,
                paymentId, convertToEntity(resource)));
    }

    @DeleteMapping("/payments/{paymentId}")
    public ResponseEntity<?> deletePayment(
            @PathVariable(name = "commerceId") Long commerceId,
            @PathVariable(name = "clientId") Long clientId,
            @PathVariable(name = "paymentId") Long paymentId){
        return paymentService.deletePayment(commerceId, clientId, paymentId);
    }

    private Payment convertToEntity(SavePaymentResource resource){ return mapper.map(resource,
            Payment.class); }

    private PaymentResource convertToResource(Payment entity){ return mapper.map(entity,
            PaymentResource.class); }
}
