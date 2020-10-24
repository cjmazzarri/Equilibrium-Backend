package com.equilibrium.webapp.domain.service;

import com.equilibrium.webapp.domain.model.Commerce;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CommerceService {
    Page<Commerce> getAllCommerces(Pageable pageable);
    Commerce getCommerceById(Long commerceId);
    Commerce createCommerce(Commerce commerce);
    Commerce updateCommerce(Long commerceId, Commerce commerceRequest);
    ResponseEntity<?> deleteCommerce(Long commerceId);
}
