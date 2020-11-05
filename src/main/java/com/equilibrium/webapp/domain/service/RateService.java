package com.equilibrium.webapp.domain.service;

import com.equilibrium.webapp.domain.model.Rate;

public interface RateService {
    Rate getRateByCommerceIdAndId(Long commerceId, Long clientId);
    Rate createRate(Long commerceId, Long clientId, Rate rate);
    Rate updateRate(Long commerceId, Long clientId, Rate request);
}
