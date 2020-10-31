package com.equilibrium.webapp.domain.service;

import com.equilibrium.webapp.domain.model.MaintenanceFee;

public interface MaintenanceFeeService {
    MaintenanceFee getMaintenanceFeeById(Long clientId);
    MaintenanceFee createMaintenanceFee(Long commerceId, Long clientId, MaintenanceFee maintenanceFee);
    MaintenanceFee updateMaintenanceFee(Long maintenanceFeeId, MaintenanceFee request);
}
