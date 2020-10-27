package com.equilibrium.webapp.service;

import com.equilibrium.webapp.domain.model.*;
import com.equilibrium.webapp.domain.repository.*;
import com.equilibrium.webapp.domain.service.ClientService;
import com.equilibrium.webapp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CommerceRepository commerceRepository;

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private MaintenanceFeeRepository maintenanceFeeRepository;

    @Autowired
    private DeliveryFeeRepository deliveryFeeRepository;

    @Override
    public Page<Client> getAllClientsByCommerceId(Long commerceId, Pageable pageable) {
        return clientRepository.findByCommerceId(commerceId, pageable); }

    @Override
    public Client getClientByIdAndCommerceId(Long clientId, Long commerceId) {
        return clientRepository.findByIdAndCommerceId(clientId, commerceId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Client not found with Id " + clientId +
                                " and CommerceId " + commerceId));
    }

    @Override
    public Client createClient(Long commerceId, Client client) {
        return commerceRepository.findById(commerceId).map(commerce -> {
            client.setCommerce(commerce);
            client.setCreditAmount((float) 0.0);
            return clientRepository.save(client);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Commerce", "Id", commerceId));
    }

    @Override
    public Client updateClient(Long commerceId, Long clientId, Client clientDetails) {
        if(!commerceRepository.existsById(commerceId))
            throw new ResourceNotFoundException("Commerce", "Id", commerceId);
        return clientRepository.findById(clientId).map(client -> {
            client.setFirstName(clientDetails.getFirstName());
            client.setLastName(clientDetails.getLastName());
            client.setCurrency(clientDetails.getCurrency());
            return clientRepository.save(client);
        }).orElseThrow(() -> new ResourceNotFoundException("Client", "Id", clientId));
    }

    @Override
    public ResponseEntity<?> deleteClient(Long commerceId, Long clientId) {
        return clientRepository.findByIdAndCommerceId(clientId, commerceId).map(client -> {
            clientRepository.delete(client);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Client not found with Id " + clientId + " and CommerceId " + commerceId));
    }

    @Override
    public Client setClientRate(Long commerceId, Long clientId, Long rateId) {
        Rate rate = rateRepository.findById(rateId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Rate", "Id", rateId));
        return clientRepository.findByIdAndCommerceId(commerceId, clientId).map(client -> {
            client.setRate(rate);
            return clientRepository.save(client);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Client", "Id", clientId));
    }

    @Override
    public Client setClientMaintenanceFee(Long commerceId, Long clientId, Long maintenanceFeeId) {
        MaintenanceFee maintenanceFee = maintenanceFeeRepository.findById(maintenanceFeeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Maintenance Fee", "Id", maintenanceFeeId));
        return clientRepository.findByIdAndCommerceId(commerceId, clientId).map(client -> {
            client.setMaintenanceFee(maintenanceFee);
            return clientRepository.save(client);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Client", "Id", clientId));
    }

    @Override
    public Client setClientDeliveryFee(Long commerceId, Long clientId, Long deliveryFeeId) {
        DeliveryFee deliveryFee = deliveryFeeRepository.findById(deliveryFeeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Delivery Fee", "Id", deliveryFeeId));
        return clientRepository.findByIdAndCommerceId(commerceId, clientId).map(client -> {
            client.setDeliveryFee(deliveryFee);
            return clientRepository.save(client);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Client", "Id", clientId));
    }

}
