package com.sror.pricing.service;

import com.sror.pricing.model.Price;
import com.sror.pricing.repository.PriceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PricingService {

    PriceRepository repository;

    public PricingService(PriceRepository repository) {
        this.repository = repository;
    }

    public Price get(Long vehicleId) throws PriceException {
        return repository.findById(vehicleId).orElseThrow(() -> new PriceException("price not found "));


    }

    public List<Price> getAll() {
        return repository.findAll();

    }
}
