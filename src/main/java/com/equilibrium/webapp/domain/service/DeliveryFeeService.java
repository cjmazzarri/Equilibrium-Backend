package com.equilibrium.webapp.domain.service;

import com.equilibrium.webapp.domain.model.DeliveryFee;

public interface DeliveryFeeService {
    DeliveryFee createDeliveryFee(Long commerceId, Long clientId, DeliveryFee deliveryFee);
    DeliveryFee getDeliveryFeeById(Long deliveryFeeId);
    DeliveryFee updateDeliveryFee(Long deliveryFeeId, DeliveryFee deliveryFeeRequest);
}
