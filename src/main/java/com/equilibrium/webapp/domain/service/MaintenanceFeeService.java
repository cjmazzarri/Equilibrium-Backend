package com.equilibrium.webapp.domain.service;

import com.equilibrium.webapp.domain.model.MaintenanceFee;
import org.springframework.http.ResponseEntity;

public interface MaintenanceFeeService {
    MaintenanceFee getMaintenanceFeeById(Long maintenanceFeeId);
    MaintenanceFee createMaintenanceFee(MaintenanceFee maintenanceFee);
    MaintenanceFee updateMaintenanceFee(Long maintenanceFeeId, MaintenanceFee request);
}
