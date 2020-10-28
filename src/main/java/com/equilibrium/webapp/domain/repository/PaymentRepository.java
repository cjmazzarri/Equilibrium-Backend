package com.equilibrium.webapp.domain.repository;

import com.equilibrium.webapp.domain.model.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Page<Payment> findByClientId(Long clientId, Pageable pageable);
    Optional<Payment> findByIdAndClientId(Long id, Long clientId);
}
