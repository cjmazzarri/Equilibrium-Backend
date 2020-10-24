package com.equilibrium.webapp.service;

import com.equilibrium.webapp.domain.model.MaintenanceFee;
import com.equilibrium.webapp.domain.repository.ClientRepository;
import com.equilibrium.webapp.domain.repository.MaintenanceFeeRepository;
import com.equilibrium.webapp.domain.service.MaintenanceFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MaintenanceFeeServiceImpl implements MaintenanceFeeService {

    @Autowired
    private MaintenanceFeeRepository maintenanceFeeRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public MaintenanceFee getMaintenanceFeeByClientId(Long clientId) {
        return null;
    }

    @Override
    public MaintenanceFee createMaintenanceFee(MaintenanceFee maintenanceFee) {
        return null;
    }

    @Override
    public MaintenanceFee updateMaintenanceFee(Long clientId, MaintenanceFee request) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteMaintenanceFee(Long clientId) {
        return null;
    }
}
