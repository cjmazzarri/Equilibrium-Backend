package com.equilibrium.webapp.controller;

import com.equilibrium.webapp.domain.model.Client;
import com.equilibrium.webapp.domain.model.MaintenanceFee;
import com.equilibrium.webapp.domain.service.MaintenanceFeeService;
import com.equilibrium.webapp.resource.ClientResource;
import com.equilibrium.webapp.resource.MaintenanceFeeResource;
import com.equilibrium.webapp.resource.SaveClientResource;
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
    private ModelMapper mapper;

    @GetMapping("/maintenanceFees")
    public MaintenanceFeeResource getMaintenanceFeeByClientId(@PathVariable(name = "clientId")
                                 Long clientId) {
        return convertToResource(maintenanceFeeService.getMaintenanceFeeById(clientId));
    }

    @PostMapping("/maintenanceFees")
    public MaintenanceFeeResource createMaintenanceFee(@PathVariable(name = "clientId")
    Long clientId, @PathVariable(name = "commerceId") Long commerceId, @Valid @RequestBody
            SaveMaintenanceFeeResource resource){
        MaintenanceFee maintenanceFee = convertToEntity(resource);
        return convertToResource(maintenanceFeeService.createMaintenanceFee(commerceId, clientId,
                maintenanceFee));
    }

    @PutMapping("/maintenanceFees")
    public MaintenanceFeeResource updateMaintenanceFee(@PathVariable(name = "commerceId") Long commerceId,
                                                       @PathVariable(name = "clientId") Long clientId,
                                               @Valid  @RequestBody SaveMaintenanceFeeResource resource) {
        MaintenanceFee maintenanceFee = convertToEntity(resource);
        return convertToResource(maintenanceFeeService.updateMaintenanceFee(clientId, maintenanceFee));
    }

    private MaintenanceFee convertToEntity(SaveMaintenanceFeeResource resource){ return mapper.map(resource,
            MaintenanceFee.class); }

    private MaintenanceFeeResource convertToResource(MaintenanceFee entity){ return mapper.map(entity,
            MaintenanceFeeResource.class); }
}
