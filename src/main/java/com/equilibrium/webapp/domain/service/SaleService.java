package com.equilibrium.webapp.domain.service;

import com.equilibrium.webapp.domain.model.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SaleService {
    Page<Sale> getAllSalesByCommerceIdAndClientId(Long commerceId, Long clientId, Pageable pageable);
    Sale getSaleByCommerceIdAndClientIdAndId(Long commerceId, Long clientId, Long saleId);
    Sale getLastSaleByCommerceIdAndClientId(Long commerceId, Long clientId);
    Sale createSale(Long commerceId, Long clientId, Sale sale);
    Sale updateSale(Long commerceId, Long clientId, Long saleId, Sale request);
    ResponseEntity<?> deleteSale(Long commerceId, Long saleId, Long clientId);
}
