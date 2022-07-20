package com.sror.pricing.controller;

import com.sror.pricing.model.Price;
import com.sror.pricing.service.PriceException;
import com.sror.pricing.service.PricingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Implements a REST-based controller for the pricing service.
 */
@RestController
@RequestMapping("/services/price")
public class PricingController {

    PricingService pricingService;

    public PricingController(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    /**
     * Gets the price for a requested vehicle.
     *
     * @param vehicleId ID number of the vehicle for which the price is requested
     * @return price of the vehicle, or error that it was not found.
     */
    @GetMapping
    public ResponseEntity<Price> get(@RequestParam Long vehicleId) throws PriceException {
        return new ResponseEntity<>(pricingService.get(vehicleId), HttpStatus.OK);

    }

    @GetMapping("/All")
    public ResponseEntity<List<Price>> getAll() {
        return new ResponseEntity<>(pricingService.getAll(), HttpStatus.OK);
    }
}
