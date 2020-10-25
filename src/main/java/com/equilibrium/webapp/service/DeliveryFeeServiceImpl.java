package com.equilibrium.webapp.service;

import com.equilibrium.webapp.domain.model.DeliveryFee;
import com.equilibrium.webapp.domain.repository.DeliveryFeeRepository;
import com.equilibrium.webapp.domain.service.DeliveryFeeService;
import com.equilibrium.webapp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryFeeServiceImpl implements DeliveryFeeService {

    @Autowired
    DeliveryFeeRepository deliveryFeeRepository;

    @Override
    public DeliveryFee createDeliveryFee(Long clientId, DeliveryFee deliveryFee) {
        deliveryFee.setId(clientId);
        return deliveryFeeRepository.save(deliveryFee);
    }

    @Override
    public DeliveryFee getDeliveryFeeById(Long deliveryFeeId) {

        return deliveryFeeRepository.findById(deliveryFeeId).
                orElseThrow(() -> new ResourceNotFoundException(
                "Delivery fee", "Id", deliveryFeeId));
    }

    @Override
    public DeliveryFee updateDeliveryFee(Long deliveryFeeId, DeliveryFee deliveryFeeRequest) {
        return deliveryFeeRepository.findById(deliveryFeeId).map(deliveryFee -> {
            deliveryFee.setFrequency(deliveryFeeRequest.getFrequency());
            deliveryFee.setType(deliveryFeeRequest.getType());
            deliveryFee.setValue(deliveryFeeRequest.getValue());
            return deliveryFeeRepository.save(deliveryFee);
        }).orElseThrow(() -> new ResourceNotFoundException("Delivery Fee", "Id", deliveryFeeId));
    }
}
