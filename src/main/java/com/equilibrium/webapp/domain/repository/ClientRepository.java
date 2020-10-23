package com.equilibrium.webapp.domain.repository;

import com.equilibrium.webapp.domain.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Page<Client> findByCommerceId(Long commerceId, Pageable pageable);
    Optional<Client> findByIdAndCommerceId(Long commerceId, Long id);
}
