package com.equilibrium.webapp.domain.repository;

import com.equilibrium.webapp.domain.model.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> { }
