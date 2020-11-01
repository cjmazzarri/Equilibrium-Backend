package com.equilibrium.webapp.service;

import com.equilibrium.webapp.domain.model.Sale;
import com.equilibrium.webapp.domain.repository.ClientRepository;
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

    @Override
    public Page<Sale> getAllSalesByClientId(Long clientId, Pageable pageable) {
        if(!clientRepository.existsById(clientId))
            throw new ResourceNotFoundException("Client", "Id", clientId);
        return saleRepository.findByClientId(clientId, pageable);
    }

    @Override
    public Sale getSaleByIdAndClientId(Long saleId, Long clientId) {
        return saleRepository.findByIdAndClientId(saleId, clientId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Sale not found with Id " + saleId +
                                " and ClientId " + clientId));
    }

    @Override
    public Sale createSale(Long clientId, Sale sale) {
        return clientRepository.findById(clientId).map(client -> {
            sale.setClient(client);
            client.setCreditAmount(client.getCreditAmount()+sale.getAmount());
            clientRepository.save(client);
            return saleRepository.save(sale);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Client", "Id", clientId));
    }

    @Override
    public Sale updateSale(Long clientId, Long saleId, Sale request) {
        if(!clientRepository.existsById(clientId))
            throw new ResourceNotFoundException("Client", "Id", clientId);
        return saleRepository.findById(saleId).map(sale -> {
            sale.setAmount(request.getAmount());
            sale.setDescription(request.getDescription());
            return saleRepository.save(sale);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Sale", "Id", saleId));
    }

    @Override
    public ResponseEntity<?> deleteSale(Long saleId, Long clientId) {
        return saleRepository.findByIdAndClientId(saleId, clientId).map(sale -> {
            saleRepository.delete(sale);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Sale not found with Id " + saleId + " and ClientId " + clientId));
    }
}
