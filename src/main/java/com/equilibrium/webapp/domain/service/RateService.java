package com.equilibrium.webapp.domain.service;

import com.equilibrium.webapp.domain.model.Rate;

public interface RateService {
    Rate getRateById(Long rateId);
    Rate createRate(Long clientId, Rate rate);
    Rate updateRate(Long rateId, Rate request);
}
