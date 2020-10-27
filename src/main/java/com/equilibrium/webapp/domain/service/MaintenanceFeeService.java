package com.equilibrium.webapp.domain.service;

import com.equilibrium.webapp.domain.model.MaintenanceFee;
import org.springframework.http.ResponseEntity;

public interface MaintenanceFeeService {
    MaintenanceFee getMaintenanceFeeByClientId(Long clientId, Long commerceId);
    MaintenanceFee createMaintenanceFee(Long commerceId, Long clientId, MaintenanceFee maintenanceFee);
    MaintenanceFee updateMaintenanceFee(Long maintenanceFeeId, MaintenanceFee request);
}
