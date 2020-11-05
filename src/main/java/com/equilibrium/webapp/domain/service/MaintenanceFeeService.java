package com.equilibrium.webapp.domain.service;

import com.equilibrium.webapp.domain.model.MaintenanceFee;

public interface MaintenanceFeeService {
    MaintenanceFee getMaintenanceFeeByCommerceIdAndId(Long commerceId, Long clientId);
    MaintenanceFee createMaintenanceFee(Long commerceId, Long clientId, MaintenanceFee maintenanceFee);
    MaintenanceFee updateMaintenanceFee(Long commerceId, Long clientId, MaintenanceFee request);
}
