package com.equilibrium.webapp.domain.repository;

import com.equilibrium.webapp.domain.model.DeliveryFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryFeeRepository extends JpaRepository<DeliveryFee, Long> {
    Optional<DeliveryFee> findByClientId(Long clientId);
}
