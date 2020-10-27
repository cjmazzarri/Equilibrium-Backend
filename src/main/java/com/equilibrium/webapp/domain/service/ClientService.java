package com.equilibrium.webapp.domain.service;

import com.equilibrium.webapp.domain.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface ClientService {
    Page<Client> getAllClientsByCommerceId(Long commerceId, Pageable pageable);
    Client getClientByIdAndCommerceId(Long id, Long commerceId);
    Client createClient(Long commerceId, Client client);
    Client updateClient(Long commerceId, Long clientId, Client clientDetails);
    ResponseEntity<?> deleteClient(Long commerceId, Long clientId);
    Client setClientRate(Long commerceId, Long clientId, Long rateId);
    Client setClientMaintenanceFee(Long commerceId, Long clientId, Long maintenanceFeeId);
    Client setClientDeliveryFee(Long commerceId, Long clientId, Long deliveryFeeId);
}
