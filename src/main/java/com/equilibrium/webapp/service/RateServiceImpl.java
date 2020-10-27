package com.equilibrium.webapp.service;

import com.equilibrium.webapp.domain.model.Rate;
import com.equilibrium.webapp.domain.repository.RateRepository;
import com.equilibrium.webapp.domain.service.RateService;
import com.equilibrium.webapp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository rateRepository;

    @Override
    public Rate getRateById(Long rateId) {
        return rateRepository.findById(rateId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Rate", "Id", rateId));
    }

    @Override
    public Rate createRate(Long clientId, Rate rate) {
        rate.setId(clientId);
        return rateRepository.save(rate);
    }

    @Override
    public Rate updateRate(Long rateId, Rate request) {
        Rate rate = rateRepository.findById(rateId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Rate", "Id", rateId));
        rate.setValue(request.getValue());
        rate.setPeriod(request.getPeriod());
        rate.setType(request.getType());
        rate.setCapitalization(request.getCapitalization());
        return rateRepository.save(rate);
    }
}
