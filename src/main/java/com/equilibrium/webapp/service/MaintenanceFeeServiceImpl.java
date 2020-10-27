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
    public MaintenanceFee getMaintenanceFeeByClientId(Long maintenanceFeeId, Long commerceId) {
        return maintenanceFeeRepository.findByClientId(maintenanceFeeId, commerceId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Maintenance fee not found with Id" + maintenanceFeeId +
                        "and Commerce Id" + commerceId));
    }

    @Override
    public MaintenanceFee createMaintenanceFee(Long commerceId, Long clientId,
                                               MaintenanceFee maintenanceFee) {
        return clientRepository.findByIdAndCommerceId(clientId, commerceId).map(client -> {
            maintenanceFee.setId(clientId);
            maintenanceFee.setClient(client);
            return maintenanceFeeRepository.save(maintenanceFee);
        }).orElseThrow(() -> new ResourceNotFoundException("Client", "Id", clientId));
    }

    @Override
    public MaintenanceFee updateMaintenanceFee(Long maintenanceFeeId, MaintenanceFee request) {
        if(!clientRepository.existsById(maintenanceFeeId))
            throw new ResourceNotFoundException("Client", "Id", maintenanceFeeId);
        return maintenanceFeeRepository.findById(maintenanceFeeId).map(maintenanceFee -> {
            maintenanceFee.setPeriod(request.getPeriod());
            maintenanceFee.setValue(request.getValue());
            return maintenanceFeeRepository.save(maintenanceFee);
        }).orElseThrow(() -> new ResourceNotFoundException("Maintenance fee", "Id", maintenanceFeeId));
    }
}
