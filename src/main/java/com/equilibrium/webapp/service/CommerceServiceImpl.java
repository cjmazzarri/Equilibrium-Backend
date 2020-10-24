package com.equilibrium.webapp.service;

import com.equilibrium.webapp.domain.model.Commerce;
import com.equilibrium.webapp.domain.repository.CommerceRepository;
import com.equilibrium.webapp.domain.service.CommerceService;
import com.equilibrium.webapp.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CommerceServiceImpl implements CommerceService {

    @Autowired
    private CommerceRepository commerceRepository;

    @Override
    public Page<Commerce> getAllCommerces(Pageable pageable) {
        return commerceRepository.findAll(pageable); }

    @Override
    public Commerce getCommerceById(Long commerceId) {
        return commerceRepository.findById(commerceId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Commerce not found with Id " + commerceId));
    }

    @Override
    public Commerce createCommerce(Commerce commerce) {
        return commerceRepository.save(commerce); }

    @Override
    public Commerce updateCommerce(Long commerceId, Commerce commerceRequest) {
        Commerce commerce = commerceRepository.findById(commerceId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Commerce", "Id", commerceId));
        commerce.setFirstName(commerceRequest.getFirstName());
        commerce.setLastName(commerceRequest.getLastName());
        commerce.setUsername(commerceRequest.getUsername());
        commerce.setPassword(commerceRequest.getPassword());
        commerce.setEmailAddress(commerceRequest.getEmailAddress());
        commerce.setBirthDate(commerceRequest.getBirthDate());
        return commerceRepository.save(commerce);
    }

    @Override
    public ResponseEntity<?> deleteCommerce(Long commerceId) {
        return commerceRepository.findById(commerceId).map(commerce -> {
            commerceRepository.delete(commerce);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Commerce not found with Id " + commerceId));
    }
}
