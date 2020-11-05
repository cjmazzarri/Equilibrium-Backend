package com.equilibrium.webapp.service;

import com.equilibrium.webapp.domain.model.Client;
import com.equilibrium.webapp.domain.model.MaintenanceFee;
import com.equilibrium.webapp.domain.repository.ClientRepository;
import com.equilibrium.webapp.domain.repository.MaintenanceFeeRepository;
import com.equilibrium.webapp.domain.service.MaintenanceFeeService;
import com.equilibrium.webapp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaintenanceFeeServiceImpl implements MaintenanceFeeService {

    @Autowired
    private MaintenanceFeeRepository maintenanceFeeRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public MaintenanceFee getMaintenanceFeeByCommerceIdAndId(Long commerceId, Long clientId) {
        if(!clientRepository.existsByIdAndCommerceId(clientId, commerceId)){
            throw new ResourceNotFoundException(
                    "Client not found with Id " + clientId +
                            " and CommerceId " + commerceId);
        }
        return maintenanceFeeRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Maintenance fee not found for client with Id " + clientId +
                                " and CommerceId " + commerceId));
    }

    @Override
    public MaintenanceFee createMaintenanceFee(Long commerceId, Long clientId,
                                               MaintenanceFee maintenanceFee) {
        Client client=clientRepository.findByIdAndCommerceId(clientId, commerceId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Client not found with Id " + clientId +
                                " and CommerceId " + commerceId));
        maintenanceFee.setId(clientId);
        maintenanceFee.setClient(client);
        return maintenanceFeeRepository.save(maintenanceFee);
    }

    @Override
    public MaintenanceFee updateMaintenanceFee(Long commerceId,
                                               Long clientId, MaintenanceFee request) {
        if(!clientRepository.existsByIdAndCommerceId(clientId, commerceId)){
            throw new ResourceNotFoundException(
                    "Client not found with Id " + clientId +
                            " and CommerceId " + commerceId);
        }
        MaintenanceFee maintenanceFee = maintenanceFeeRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Maintenance fee not found for client with Id " + clientId +
                                " and CommerceId " + commerceId));
        maintenanceFee.setPeriod(request.getPeriod());
        maintenanceFee.setValue(request.getValue());
        return maintenanceFeeRepository.save(maintenanceFee);
    }
}
