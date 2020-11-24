package com.equilibrium.webapp.controller;

import com.equilibrium.webapp.domain.model.Movement;
import com.equilibrium.webapp.domain.model.Payment;
import com.equilibrium.webapp.domain.service.MovementService;
import com.equilibrium.webapp.resource.MovementResource;
import com.equilibrium.webapp.resource.PaymentResource;
import com.equilibrium.webapp.resource.SaveMovementResource;
import com.equilibrium.webapp.resource.SavePaymentResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Movements", description = "Movements API")
@RestController
@RequestMapping("/api/commerces/{commerceId}/clients/{clientId}")
@CrossOrigin
public class MovementController {

    @Autowired
    private MovementService movementService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/movements")
    public Page<MovementResource> getAllMovementsByCommerceIdAndClientId(
            @PathVariable(name = "commerceId") Long commerceId,
            @PathVariable(name = "clientId") Long clientId, Pageable pageable){
        Page<Movement> movementPage=movementService.getAllMovementsByCommerceIdAndClientId(commerceId, clientId, pageable);
        List<MovementResource> resources=movementPage.getContent().stream()
                .map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private Movement convertToEntity(SaveMovementResource resource){ return mapper.map(resource,
            Movement.class); }

    private MovementResource convertToResource(Movement entity){ return mapper.map(entity,
            MovementResource.class); }
}
