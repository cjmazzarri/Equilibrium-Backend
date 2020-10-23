package com.equilibrium.webapp.domain.repository;

import com.equilibrium.webapp.domain.model.Commerce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommerceRepository extends JpaRepository<Commerce, Long> { }
