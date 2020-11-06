package com.equilibrium.webapp.domain.repository;

import com.equilibrium.webapp.domain.model.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    Page<Sale> findByClientId(Long clientId, Pageable pageable);
    Optional<Sale> findByIdAndClientId(Long id, Long clientId);
    Optional<Sale> findTopByOrderByIdDesc();
}
