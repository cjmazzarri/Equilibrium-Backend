package com.equilibrium.webapp.domain.service;

import com.equilibrium.webapp.domain.model.DeliveryFee;
import org.springframework.http.ResponseEntity;

public interface DeliveryFeeService {
    DeliveryFee createDeliveryFee(DeliveryFee deliveryFee);
    DeliveryFee getDeliveryFeeById(Long deliveryFeeId);
    DeliveryFee updateDeliveryFee(Long deliveryFeeId, DeliveryFee deliveryFeeRequest);
}
