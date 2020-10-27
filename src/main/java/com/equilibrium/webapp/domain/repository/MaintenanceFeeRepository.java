package com.equilibrium.webapp.domain.repository;

import com.equilibrium.webapp.domain.model.MaintenanceFee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceFeeRepository extends JpaRepository<MaintenanceFee, Long> {
}
