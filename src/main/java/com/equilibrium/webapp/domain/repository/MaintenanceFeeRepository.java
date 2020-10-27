package com.equilibrium.webapp.domain.repository;

import com.equilibrium.webapp.domain.model.MaintenanceFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MaintenanceFeeRepository extends JpaRepository<MaintenanceFee, Long> {
    Optional<MaintenanceFee> findByClientId(Long clientId, Long commerceId);
}
