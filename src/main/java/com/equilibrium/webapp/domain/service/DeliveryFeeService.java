package com.equilibrium.webapp.domain.service;

import com.equilibrium.webapp.domain.model.DeliveryFee;
import org.springframework.http.ResponseEntity;

public interface DeliveryFeeService {
    DeliveryFee createDeliveryFee(Long clientId, DeliveryFee deliveryFee);
    DeliveryFee getDeliveryFeeByClientId(Long clientId);
    DeliveryFee updateDeliveryFee(Long clientId, DeliveryFee deliveryFeeRequest);
    ResponseEntity<?> deleteDeliveryFee(Long clientId);
}
