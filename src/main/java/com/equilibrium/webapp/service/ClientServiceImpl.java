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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private CommerceRepository commerceRepository;

    @Autowired
    private MovementRepository movementRepository;

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
    public Client setClientRate(Long commerceId, Long clientId, Rate rate) {
        return clientRepository.findByIdAndCommerceId(clientId, commerceId).map(client -> {
            client.setRate(rate);
            return clientRepository.save(client);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Client", "Id", clientId));
    }

    @Override
    public Client setClientMaintenanceFee(Long commerceId, Long clientId, MaintenanceFee maintenanceFee) {
        return clientRepository.findByIdAndCommerceId(clientId, commerceId).map(client -> {
            client.setMaintenanceFee(maintenanceFee);
            return clientRepository.save(client);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Client", "Id", clientId));
    }

    @Override
    public Client setClientDeliveryFee(Long commerceId, Long clientId, DeliveryFee deliveryFee) {
        return clientRepository.findByIdAndCommerceId(clientId, commerceId).map(client -> {
            client.setDeliveryFee(deliveryFee);
            return clientRepository.save(client);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Client", "Id", clientId));
    }

    @Override
    public ResponseEntity<?> nextDay() {
        Page<Client> clientPage=clientRepository.findAll(Pageable.unpaged());
        List<Client> clientList=clientPage.getContent().stream().peek(client -> {
            client.getRate().setRealRate();
            client.setActiveDays(client.getActiveDays()+1);
            //Delivery Fee operation
            Float creditAmount=client.getCreditAmount();
            Float deliveryFee=client.getDeliveryFee().getValue();
            if(client.getDeliveryFee().getType().equals("Periodo")){
                if (client.feeTrigger(client.getDeliveryFee().getFrequency())) {
                    client.setCreditAmount(creditAmount + deliveryFee);
                    movementRepository.save(new Movement(client, "Delivery por periodo", deliveryFee));
                }
            }
            //Maintenance Fee operation
            creditAmount=client.getCreditAmount();
            Float maintenanceFee=client.getMaintenanceFee().getValue();
            switch (client.getMaintenanceFee().getPeriod()){
                case "s":
                    if (client.feeTrigger(7)) {
                        client.setCreditAmount(creditAmount + maintenanceFee);
                        movementRepository.save(new Movement(client, "Mantenimiento semanal", maintenanceFee));
                    }
                    break;
                case "q":
                    if (client.feeTrigger(15)) {
                        client.setCreditAmount(creditAmount + maintenanceFee);
                        movementRepository.save(new Movement(client, "Mantenimiento quincenal", maintenanceFee));
                    }
                    break;
                case "m":
                    if (client.feeTrigger(30)) {
                        client.setCreditAmount(creditAmount + maintenanceFee);
                        movementRepository.save(new Movement(client, "Mantenimiento mensual", maintenanceFee));
                    }
                    break;
            }
            //Rate operation
            creditAmount=client.getCreditAmount();
            if (client.getCreditAmount()!=0) {
                client.setCreditAmount((float) (Math.round
                        ((creditAmount * (1d + client.getRate().getRealRate()) * 100.0)) / 100.0));
                movementRepository.save(new Movement(client, "Interes generado", client.getCreditAmount() - creditAmount));
            }
            clientRepository.save(client);
        }).collect(Collectors.toList());
        return ResponseEntity.ok().build();
    }

}
