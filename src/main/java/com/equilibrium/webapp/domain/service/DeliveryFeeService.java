package com.equilibrium.webapp.domain.service;

import com.equilibrium.webapp.domain.model.DeliveryFee;

public interface DeliveryFeeService {
    DeliveryFee getDeliveryFeeByCommerceIdAndId(Long commerceId, Long clientId);
    DeliveryFee createDeliveryFee(Long commerceId, Long clientId, DeliveryFee deliveryFee);
    DeliveryFee updateDeliveryFee(Long commerceId, Long clientId, DeliveryFee deliveryFeeRequest);
}
