package com.equilibrium.webapp.service;

import com.equilibrium.webapp.domain.model.Client;
import com.equilibrium.webapp.domain.model.DeliveryFee;
import com.equilibrium.webapp.domain.repository.ClientRepository;
import com.equilibrium.webapp.domain.repository.DeliveryFeeRepository;
import com.equilibrium.webapp.domain.service.DeliveryFeeService;
import com.equilibrium.webapp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryFeeServiceImpl implements DeliveryFeeService {

    @Autowired
    DeliveryFeeRepository deliveryFeeRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public DeliveryFee getDeliveryFeeByCommerceIdAndId(Long commerceId, Long clientId) {
        if(!clientRepository.existsByIdAndCommerceId(clientId, commerceId)){
            throw new ResourceNotFoundException(
                    "Client not found with Id " + clientId +
                            " and CommerceId " + commerceId);
        }
        return deliveryFeeRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Delivery fee not found for client with Id " + clientId +
                                " and CommerceId " + commerceId));
    }

    @Override
    public DeliveryFee createDeliveryFee(Long commerceId, Long clientId, DeliveryFee deliveryFee) {
        Client client=clientRepository.findByIdAndCommerceId(clientId, commerceId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Client not found with Id " + clientId +
                                " and CommerceId " + commerceId));
        deliveryFee.setId(clientId);
        deliveryFee.setClient(client);
        return deliveryFeeRepository.save(deliveryFee);
    }

    @Override
    public DeliveryFee updateDeliveryFee(Long commerceId, Long clientId, DeliveryFee deliveryFeeRequest) {
        if(!clientRepository.existsByIdAndCommerceId(clientId, commerceId)){
            throw new ResourceNotFoundException(
                    "Client not found with Id " + clientId +
                            " and CommerceId " + commerceId);
        }
        DeliveryFee deliveryFee = deliveryFeeRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Delivery fee not found for client with Id " + clientId +
                                " and CommerceId " + commerceId));
        deliveryFee.setFrequency(deliveryFeeRequest.getFrequency());
        deliveryFee.setType(deliveryFeeRequest.getType());
        deliveryFee.setValue(deliveryFeeRequest.getValue());
        return deliveryFeeRepository.save(deliveryFee);
    }
}
