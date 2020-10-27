package com.equilibrium.webapp.service;

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
    public MaintenanceFee getMaintenanceFeeById(Long maintenanceFeeId) {
        return maintenanceFeeRepository.findById(maintenanceFeeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Maintenance fee", "Id", maintenanceFeeId));
    }

    @Override
    public MaintenanceFee createMaintenanceFee(Long commerceId, Long clientId,
                                               MaintenanceFee maintenanceFee) {
        return clientRepository.findByIdAndCommerceId(clientId, commerceId).map(client -> {
            maintenanceFee.setId(clientId);
            maintenanceFee.setClient(client);
            return maintenanceFeeRepository.save(maintenanceFee);
        }).orElseThrow(() -> new ResourceNotFoundException("Client not found with id" + clientId +
                " and Commerce Id" + commerceId));
    }

    @Override
    public MaintenanceFee updateMaintenanceFee(Long maintenanceFeeId, MaintenanceFee request) {
        MaintenanceFee maintenanceFee = maintenanceFeeRepository.findById(maintenanceFeeId).
                orElseThrow( () -> new ResourceNotFoundException("Maintenance Fee", "Id",
                        maintenanceFeeId));
        maintenanceFee.setPeriod(request.getPeriod());
        maintenanceFee.setValue(request.getValue());
        return maintenanceFeeRepository.save(maintenanceFee);
    }
}
