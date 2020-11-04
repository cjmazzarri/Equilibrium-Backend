package com.equilibrium.webapp.controller;

import com.equilibrium.webapp.domain.model.Rate;
import com.equilibrium.webapp.domain.service.ClientService;
import com.equilibrium.webapp.domain.service.RateService;
import com.equilibrium.webapp.resource.RateResource;
import com.equilibrium.webapp.resource.SaveRateResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Rates", description = "Rates API")
@RestController
@RequestMapping("/api/commerces/{commerceId}/clients/{clientId}")
public class RateController {

    @Autowired
    private RateService rateService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/rates")
    public RateResource getRateByClientId(
            @PathVariable(name = "clientId") Long clientId){
        return convertToResource(rateService.getRateById(clientId));
    }

    @PostMapping("/rates")
    public RateResource createRate(
            @PathVariable(name = "commerceId") Long commerceId,
            @PathVariable(name = "clientId") Long clientId,
            @Valid @RequestBody SaveRateResource resource){
        Rate rate=rateService.createRate(commerceId, clientId, convertToEntity(resource));
        clientService.setClientRate(commerceId, clientId, rate);
        return convertToResource(rateService.createRate(commerceId, clientId, rate));
    }

    @PutMapping("/rates")
    public RateResource updateRate(
            @PathVariable(name = "clientId") Long clientId,
            @Valid @RequestBody SaveRateResource resource){
        return convertToResource(rateService.updateRate(clientId, convertToEntity(resource)));
    }

    private Rate convertToEntity(SaveRateResource resource) {
        return mapper.map(resource, Rate.class);
    }

    private RateResource convertToResource(Rate entity) {
        return mapper.map(entity, RateResource.class);
    }
}
