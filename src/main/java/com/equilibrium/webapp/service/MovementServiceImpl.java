package com.equilibrium.webapp.service;

import com.equilibrium.webapp.domain.model.Movement;
import com.equilibrium.webapp.domain.repository.ClientRepository;
import com.equilibrium.webapp.domain.repository.MovementRepository;
import com.equilibrium.webapp.domain.service.MovementService;
import com.equilibrium.webapp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MovementServiceImpl implements MovementService {

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Page<Movement> getAllMovementsByCommerceIdAndClientId(Long commerceId, Long clientId, Pageable pageable) {
        this.validateClient(clientId, commerceId);
        return movementRepository.findByClientIdOrderByIdDesc(clientId, pageable);
    }

    public void validateClient(Long clientId, Long commerceId){
        if(!clientRepository.existsByIdAndCommerceId(clientId, commerceId)){
            throw new ResourceNotFoundException(
                    "Client not found with Id " + clientId +
                            " and CommerceId " + commerceId);}
    }
}
