package com.equilibrium.webapp.controller;

import com.equilibrium.webapp.domain.model.DeliveryFee;
import com.equilibrium.webapp.domain.service.ClientService;
import com.equilibrium.webapp.domain.service.DeliveryFeeService;
import com.equilibrium.webapp.resource.DeliveryFeeResource;
import com.equilibrium.webapp.resource.SaveDeliveryFeeResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Delivery Fees", description = "Delivery Fees API")
@RestController
@RequestMapping("/api/commerces/{commerceId}/clients/{clientId}")
public class DeliveryFeeController {

    @Autowired
    private DeliveryFeeService deliveryFeeService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/deliveryFees")
    public DeliveryFeeResource getDeliveryFeeByClientId(
            @PathVariable(name = "clientId") Long clientId){
        return convertToResource(deliveryFeeService.getDeliveryFeeById(clientId));
    }

    @PostMapping("/deliveryFees")
    public DeliveryFeeResource createDeliveryFee(
            @PathVariable(name = "commerceId") Long commerceId,
            @PathVariable(name = "clientId") Long clientId,
            @Valid @RequestBody SaveDeliveryFeeResource resource){
        DeliveryFee deliveryFee=deliveryFeeService.createDeliveryFee(commerceId, clientId, convertToEntity(resource));
        clientService.setClientDeliveryFee(commerceId, clientId, deliveryFee);
        return convertToResource(deliveryFee);
    }

    @PutMapping("/deliveryFees")
    public DeliveryFeeResource updateDeliveryFee(
            @PathVariable(name = "clientId") Long clientId,
            @Valid @RequestBody SaveDeliveryFeeResource resource){
        return convertToResource(deliveryFeeService.updateDeliveryFee(clientId, convertToEntity(resource)));
    }

    private DeliveryFee convertToEntity(SaveDeliveryFeeResource resource) {
        return mapper.map(resource, DeliveryFee.class);
    }

    private DeliveryFeeResource convertToResource(DeliveryFee entity) {
        return mapper.map(entity, DeliveryFeeResource.class);
    }
}
