package com.equilibrium.webapp.domain.service;

import com.equilibrium.webapp.domain.model.MaintenanceFee;
import org.springframework.http.ResponseEntity;

public interface MaintenanceFeeService {
    MaintenanceFee getMaintenanceFeeByClientId(Long clientId);
    MaintenanceFee createMaintenanceFee(MaintenanceFee maintenanceFee);
    MaintenanceFee updateMaintenanceFee(Long clientId, MaintenanceFee request);
    ResponseEntity<?> deleteMaintenanceFee(Long clientId);
}
