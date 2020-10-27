package com.equilibrium.webapp.controller;

import com.equilibrium.webapp.domain.model.Client;
import com.equilibrium.webapp.domain.service.ClientService;
import com.equilibrium.webapp.resource.ClientResource;
import com.equilibrium.webapp.resource.SaveClientResource;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/clients")
    public Page<ClientResource> getAllClientsByCommerceId(Long commerceId, Pageable pageable){
        Page<Client> clientPage = clientService.getAllClientsByCommerceId(commerceId, pageable);
        List<ClientResource> resources = clientPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    /*@GetMapping("/clients/{id")
    public ClientResource getClientByIdAndCommerceId(Long commerceId, Long clientId){
        to be implemented
    }*/
    
    private Client convertToEntity(SaveClientResource resource){ return mapper.map(resource, Client.class); }

    private ClientResource convertToResource(Client entity){ return mapper.map(entity, ClientResource.class); }
}
