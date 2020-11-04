package com.equilibrium.webapp.service;

import com.equilibrium.webapp.domain.model.Client;
import com.equilibrium.webapp.domain.model.Rate;
import com.equilibrium.webapp.domain.repository.ClientRepository;
import com.equilibrium.webapp.domain.repository.RateRepository;
import com.equilibrium.webapp.domain.service.RateService;
import com.equilibrium.webapp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Rate getRateById(Long rateId) {
        return rateRepository.findById(rateId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Rate", "Id", rateId));
    }

    @Override
    public Rate createRate(Long commerceId, Long clientId, Rate rate) {
        Client client=clientRepository.findByIdAndCommerceId(clientId, commerceId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Client not found with Id " + clientId +
                                " and CommerceId " + commerceId));
        rate.setId(clientId);
        rate.setClient(client);
        rate.setRealRate();
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
        rate.setRealRate();
        return rateRepository.save(rate);
    }
}
