package com.equilibrium.webapp.controller;

import com.equilibrium.webapp.domain.model.MaintenanceFee;
import com.equilibrium.webapp.domain.service.ClientService;
import com.equilibrium.webapp.domain.service.MaintenanceFeeService;
import com.equilibrium.webapp.resource.MaintenanceFeeResource;
import com.equilibrium.webapp.resource.SaveMaintenanceFeeResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Maintenance Fees", description = "Maintenance Fees API")
@RestController
@RequestMapping("/api/commerces/{commerceId}/clients/{clientId}/")
@CrossOrigin
public class MaintenanceFeeController {
    @Autowired
    private MaintenanceFeeService maintenanceFeeService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/maintenanceFees")
    public MaintenanceFeeResource getMaintenanceFeeByCommerceIdAndClientId(
            @PathVariable(name = "commerceId") Long commerceId,
            @PathVariable(name = "clientId") Long clientId) {
        return convertToResource(maintenanceFeeService.getMaintenanceFeeByCommerceIdAndId(commerceId, clientId));
    }

    @PostMapping("/maintenanceFees")
    public MaintenanceFeeResource createMaintenanceFee(
            @PathVariable(name = "clientId") Long clientId,
            @PathVariable(name = "commerceId") Long commerceId,
            @Valid @RequestBody SaveMaintenanceFeeResource resource){
        MaintenanceFee maintenanceFee = maintenanceFeeService.createMaintenanceFee(commerceId, clientId,
                convertToEntity(resource));
        clientService.setClientMaintenanceFee(commerceId, clientId, maintenanceFee);
        return convertToResource(maintenanceFeeService.createMaintenanceFee(commerceId, clientId, maintenanceFee));
    }

    @PutMapping("/maintenanceFees")
    public MaintenanceFeeResource updateMaintenanceFee(
            @PathVariable(name = "commerceId") Long commerceId,
            @PathVariable(name = "clientId") Long clientId,
            @Valid  @RequestBody SaveMaintenanceFeeResource resource) {
        return convertToResource(maintenanceFeeService.updateMaintenanceFee(commerceId, clientId, convertToEntity(resource)));
    }

    private MaintenanceFee convertToEntity(SaveMaintenanceFeeResource resource){ return mapper.map(resource,
            MaintenanceFee.class); }

    private MaintenanceFeeResource convertToResource(MaintenanceFee entity){ return mapper.map(entity,
            MaintenanceFeeResource.class); }
}
