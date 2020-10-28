package com.equilibrium.webapp.controller;

import com.equilibrium.webapp.domain.model.Commerce;
import com.equilibrium.webapp.domain.service.CommerceService;
import com.equilibrium.webapp.resource.CommerceResource;
import com.equilibrium.webapp.resource.SaveCommerceResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class CommerceController {

    @Autowired
    private CommerceService commerceService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/commerces")
    public Page<CommerceResource> getAllCommerces(Pageable pageable){
        Page<Commerce> commercePage = commerceService.getAllCommerces(pageable);
        List<CommerceResource> resources = commercePage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/commerces/{commerceId}")
    public CommerceResource getCommerceById(@PathVariable(name = "commerceId") Long commerceId){
        return convertToResource(commerceService.getCommerceById(commerceId));
    }

    @PostMapping("/commerces")
    public CommerceResource createCommerce(@Valid @RequestBody SaveCommerceResource resource,
                                           Pageable pageable){
        Commerce commerce = convertToEntity(resource);
        return convertToResource(commerceService.createCommerce(commerce, pageable));
    }

    @PutMapping("/commerces/{commerceId}")
    public CommerceResource updateCommerce(@PathVariable Long commerceId,
                                       @Valid @RequestBody SaveCommerceResource resource){
        Commerce commerce = convertToEntity(resource);
        return convertToResource(commerceService.updateCommerce(commerceId, commerce));
    }

    @DeleteMapping("/commerces/{commerceId}")
    public ResponseEntity<?> deleteCommerce(@PathVariable Long commerceId) {return commerceService.deleteCommerce(commerceId); }

    private Commerce convertToEntity(SaveCommerceResource resource) {
        return mapper.map(resource, Commerce.class);
    }

    private CommerceResource convertToResource(Commerce entity) {
        return mapper.map(entity, CommerceResource.class);
    }
}
