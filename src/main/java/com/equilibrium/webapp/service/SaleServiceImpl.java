package com.equilibrium.webapp.service;

import com.equilibrium.webapp.domain.model.Movement;
import com.equilibrium.webapp.domain.model.Sale;
import com.equilibrium.webapp.domain.repository.ClientRepository;
import com.equilibrium.webapp.domain.repository.MovementRepository;
import com.equilibrium.webapp.domain.repository.SaleRepository;
import com.equilibrium.webapp.domain.service.SaleService;
import com.equilibrium.webapp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MovementRepository movementRepository;

    @Override
    public Page<Sale> getAllSalesByCommerceIdAndClientId(Long commerceId, Long clientId, Pageable pageable) {
        this.validateClient(clientId, commerceId);
        return saleRepository.findByClientId(clientId, pageable);
    }

    @Override
    public Sale getSaleByCommerceIdAndClientIdAndId(Long commerceId, Long clientId, Long saleId) {
        this.validateClient(clientId, commerceId);
        return saleRepository.findByIdAndClientId(saleId, clientId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Sale not found with Id " + saleId +
                                " and ClientId " + clientId));
    }

    @Override
    public Sale getLastSaleByCommerceIdAndClientId(Long commerceId, Long clientId) {
        this.validateClient(clientId, commerceId);
        return saleRepository.findTopByOrderByIdDesc()
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Last Sale not found for client with Id " + clientId));
    }

    @Override
    public Sale createSale(Long commerceId, Long clientId, Sale sale) {
        this.validateClient(clientId, commerceId);
        return clientRepository.findById(clientId).map(client -> {
            sale.setClient(client);
            client.setCreditAmount(client.getCreditAmount()+sale.getAmount());
            clientRepository.save(client);
            movementRepository.save(new Movement(client, sale.getDescription(), sale.getAmount()));
            if(client.getDeliveryFee().getType().equals("Pedido")) {
                client.setCreditAmount(client.getCreditAmount() + client.getDeliveryFee().getValue());
                movementRepository.save(new Movement(client,
                        "Delivery por entrega de "+sale.getDescription(),
                        client.getDeliveryFee().getValue()));
            }
            return saleRepository.save(sale);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Client not found with Id " + clientId));
    }

    @Override
    public Sale updateSale(Long commerceId, Long clientId, Long saleId, Sale request) {
        this.validateClient(clientId, commerceId);
        return saleRepository.findByIdAndClientId(saleId, clientId).map(sale -> {
            sale.setAmount(request.getAmount());
            sale.setDescription(request.getDescription());
            return saleRepository.save(sale);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Sale", "Id", saleId));
    }

    @Override
    public ResponseEntity<?> deleteSale(Long commerceId, Long clientId, Long saleId) {
        this.validateClient(clientId, commerceId);
        return saleRepository.findByIdAndClientId(saleId, clientId).map(sale -> {
            saleRepository.delete(sale);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Sale not found with Id " + saleId + " and ClientId " + clientId));
    }

    public void validateClient(Long clientId, Long commerceId){
        if(!clientRepository.existsByIdAndCommerceId(clientId, commerceId)){
            throw new ResourceNotFoundException(
                    "Client not found with Id " + clientId +
                            " and CommerceId " + commerceId);}
    }
}
