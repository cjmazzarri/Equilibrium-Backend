package com.equilibrium.webapp.domain.service;

import com.equilibrium.webapp.domain.model.Movement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovementService {
    Page<Movement> getAllMovementsByCommerceIdAndClientId(Long commerceId, Long clientId, Pageable pageable);
}
