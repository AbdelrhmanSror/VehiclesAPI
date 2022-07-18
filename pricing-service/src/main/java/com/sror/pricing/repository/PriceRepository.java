package com.sror.pricing.repository;

import com.sror.pricing.model.Price;
import org.springframework.stereotype.Repository;

public interface PriceRepository {
    void save(Long vehicleId, Price price);
    Price findById(Long vehicleId);

}
