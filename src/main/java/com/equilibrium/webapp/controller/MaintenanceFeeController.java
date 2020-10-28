package com.equilibrium.webapp.controller;

import com.equilibrium.webapp.domain.model.MaintenanceFee;
import com.equilibrium.webapp.domain.service.ClientService;
import com.equilibrium.webapp.domain.service.MaintenanceFeeService;
import com.equilibrium.webapp.resource.MaintenanceFeeResource;
import com.equilibrium.webapp.resource.SaveMaintenanceFeeResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/commerces/{commerceId}/clients/{clientId}/")
public class MaintenanceFeeController {
    @Autowired
    private MaintenanceFeeService maintenanceFeeService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/maintenanceFees")
    public MaintenanceFeeResource getMaintenanceFeeByClientId(@PathVariable(name = "clientId")
                                 Long clientId) {
        return convertToResource(maintenanceFeeService.getMaintenanceFeeById(clientId));
    }

    @PostMapping("/maintenanceFees")
    public MaintenanceFeeResource createMaintenanceFee(
            @PathVariable(name = "clientId") Long clientId,
            @PathVariable(name = "commerceId") Long commerceId,
            @Valid @RequestBody SaveMaintenanceFeeResource resource){
        MaintenanceFee maintenanceFee = maintenanceFeeService.createMaintenanceFee(commerceId, clientId,
                convertToEntity(resource));
        clientService.setClientMaintenanceFee(commerceId, clientId, maintenanceFee);
        return convertToResource(maintenanceFee);
    }

    @PutMapping("/maintenanceFees")
    public MaintenanceFeeResource updateMaintenanceFee(@PathVariable(name = "clientId") Long clientId,
                                               @Valid  @RequestBody SaveMaintenanceFeeResource resource) {
        return convertToResource(maintenanceFeeService.updateMaintenanceFee(clientId, convertToEntity(resource)));
    }

    private MaintenanceFee convertToEntity(SaveMaintenanceFeeResource resource){ return mapper.map(resource,
            MaintenanceFee.class); }

    private MaintenanceFeeResource convertToResource(MaintenanceFee entity){ return mapper.map(entity,
            MaintenanceFeeResource.class); }
}