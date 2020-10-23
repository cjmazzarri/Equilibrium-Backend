package com.equilibrium.webapp.domain.repository;

import com.equilibrium.webapp.domain.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Page<Client> FindByClientId(Long clientId, Pageable pageable);
    Optional<Client> FindByIdAndClientId(Long id, Long clientId);
}
