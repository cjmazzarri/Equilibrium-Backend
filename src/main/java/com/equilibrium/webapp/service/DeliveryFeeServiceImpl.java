package com.equilibrium.webapp.service;

import com.equilibrium.webapp.domain.model.DeliveryFee;
import com.equilibrium.webapp.domain.repository.ClientRepository;
import com.equilibrium.webapp.domain.repository.DeliveryFeeRepository;
import com.equilibrium.webapp.domain.service.DeliveryFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DeliveryFeeServiceImpl implements DeliveryFeeService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    DeliveryFeeRepository deliveryFeeRepository;


    @Override
    public DeliveryFee createDeliveryFee(Long clientId, DeliveryFee deliveryFee) {
        return null;
    }

    @Override
    public DeliveryFee getDeliveryFeeByClientId(Long clientId) {
        return null;
    }

    @Override
    public DeliveryFee updateDeliveryFee(Long clientId, DeliveryFee deliveryFeeRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteDeliveryFee(Long clientId) {
        return null;
    }
}
