package com.equilibrium.webapp.service;

import com.equilibrium.webapp.domain.model.DeliveryFee;
import com.equilibrium.webapp.domain.repository.DeliveryFeeRepository;
import com.equilibrium.webapp.domain.service.DeliveryFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryFeeServiceImpl implements DeliveryFeeService {

    @Autowired
    DeliveryFeeRepository deliveryFeeRepository;

    @Override
    public DeliveryFee createDeliveryFee(DeliveryFee deliveryFee) {
        return deliveryFeeRepository.save(deliveryFee);
    }

    @Override
    public DeliveryFee getDeliveryFeeById(Long deliveryFeeId) {
        return null;
    }

    @Override
    public DeliveryFee updateDeliveryFee(Long deliveryFeeId, DeliveryFee deliveryFeeRequest) {
        return null;
    }
}
