package com.equilibrium.webapp.domain.service;

import com.equilibrium.webapp.domain.model.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SaleService {
    Page<Sale> getAllSalesByClientId(Long clientId, Pageable pageable);
    Sale getSaleByIdAndClientId(Long clientId, Long saleId);
    Sale createSale(Long clientId, Sale sale);
    Sale updateSale(Long clientId, Long saleId, Sale request);
    ResponseEntity<?> deleteSale(Long clientId, Long saleId);
}
