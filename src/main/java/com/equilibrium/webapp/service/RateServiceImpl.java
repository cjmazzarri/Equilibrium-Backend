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
    public Rate getRateByCommerceIdAndId(Long commerceId, Long clientId) {
        this.validateClient(clientId, commerceId);
        return rateRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Rate not found for client with Id " + clientId +
                                " and CommerceId " + commerceId));
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
    public Rate updateRate(Long commerceId, Long clientId, Rate request) {
        this.validateClient(clientId, commerceId);
        Rate rate = rateRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Rate not found for client with Id " + clientId +
                                " and CommerceId " + commerceId));
        rate.setValue(request.getValue());
        rate.setPeriod(request.getPeriod());
        rate.setType(request.getType());
        rate.setCapitalization(request.getCapitalization());
        rate.setRealRate();
        return rateRepository.save(rate);
    }

    public void validateClient(Long clientId, Long commerceId){
        if(!clientRepository.existsByIdAndCommerceId(clientId, commerceId)){
            throw new ResourceNotFoundException(
                    "Client not found with Id " + clientId +
                            " and CommerceId " + commerceId);}
    }
}
