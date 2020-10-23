package com.equilibrium.webapp.domain.repository;

import com.equilibrium.webapp.domain.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> { }
