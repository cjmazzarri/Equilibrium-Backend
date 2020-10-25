package com.equilibrium.webapp.service;

import com.equilibrium.webapp.domain.model.MaintenanceFee;
import com.equilibrium.webapp.domain.repository.MaintenanceFeeRepository;
import com.equilibrium.webapp.domain.service.MaintenanceFeeService;
import com.equilibrium.webapp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaintenanceFeeServiceImpl implements MaintenanceFeeService {

    @Autowired
    private MaintenanceFeeRepository maintenanceFeeRepository;

    @Override
    public MaintenanceFee getMaintenanceFeeById(Long maintenanceFeeId) {
        return maintenanceFeeRepository.findById(maintenanceFeeId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Maintenance fee", "Id", maintenanceFeeId));
    }

    @Override
    public MaintenanceFee createMaintenanceFee(Long clientId, MaintenanceFee maintenanceFee) {
        return null;
    }

    @Override
    public MaintenanceFee updateMaintenanceFee(Long maintenanceFeeId, MaintenanceFee request) {
        return null;
    }

}
