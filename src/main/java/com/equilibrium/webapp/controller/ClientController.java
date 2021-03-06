package com.equilibrium.webapp.controller;

import com.equilibrium.webapp.domain.model.Client;
import com.equilibrium.webapp.domain.repository.ClientRepository;
import com.equilibrium.webapp.domain.service.ClientService;
import com.equilibrium.webapp.resource.ClientResource;
import com.equilibrium.webapp.resource.SaveClientResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Tag(name = "Clients", description = "Clients API")
@RestController
@RequestMapping("/api")
@CrossOrigin
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/commerces/{commerceId}/clients")
    public Page<ClientResource> getAllClientsByCommerceId(@PathVariable(name = "commerceId") Long commerceId,
                                                           Pageable pageable){
        Page<Client> clientPage = clientService.getAllClientsByCommerceId(commerceId, pageable);
        List<ClientResource> resources = clientPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/commerces/{commerceId}/clients/{clientId}")
    public ClientResource getClientByIdAndCommerceId(@PathVariable(name = "commerceId") Long commerceId,
                                                     @PathVariable(name = "clientId") Long clientId){
        return convertToResource(clientService.getClientByIdAndCommerceId(clientId, commerceId));
    }

    @PostMapping("/commerces/{commerceId}/clients")
    public ClientResource createClient(@PathVariable(name = "commerceId") Long commerceId,
           @Valid @RequestBody SaveClientResource resource){
        Client client = convertToEntity(resource);
        return convertToResource(clientService.createClient(commerceId, client));
    }

    @PutMapping("/commerces/{commerceId}/clients/{clientId}")
    public ClientResource updateClient(@PathVariable(name = "commerceId") Long commerceId,
                                       @PathVariable(name = "clientId") Long clientId,
                                       @Valid @RequestBody SaveClientResource resource){
        Client client = convertToEntity(resource);
        return convertToResource(clientService.updateClient(commerceId, clientId, client));
    }

    @DeleteMapping("/commerces/{commerceId}/clients/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable(name = "commerceId") Long commerceId,
                                          @PathVariable(name = "clientId") Long clientId){
        return clientService.deleteClient(commerceId, clientId);
    }

    @PutMapping("/nextday/{days}")
    public ResponseEntity<?> nextDay(@PathVariable(name = "days") Long days){
        return clientService.nextDay(days);
    }

    private Client convertToEntity(SaveClientResource resource){ return mapper.map(resource, Client.class); }

    private ClientResource convertToResource(Client entity){ return mapper.map(entity, ClientResource.class); }
}
