package com.equilibrium.webapp.domain.repository;

import com.equilibrium.webapp.domain.model.Movement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovementRepository extends JpaRepository<Movement, Long> {
    Page<Movement> findByClientIdOrderByIdDesc(Long clientId, Pageable pageable);
    Optional<Movement> findByIdAndClientId(Long id, Long clientId);
    Optional<Movement> findTopByOrderByIdDesc();
}
