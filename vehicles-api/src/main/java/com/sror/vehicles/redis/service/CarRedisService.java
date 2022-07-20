package com.sror.vehicles.redis.service;

import com.sror.vehicles.redis.model.Address;
import com.sror.vehicles.redis.model.Price;
import com.sror.vehicles.redis.repository.MapCacheRepository;
import com.sror.vehicles.redis.repository.PriceCacheRepository;
import org.springframework.stereotype.Service;

/**
 * Implements the car cache services create, read, update or delete
 * information about vehicles, as well as gather related
 * location and price data when desired.
 */
@Service
public class CarRedisService {

    //for calling the map and price services
    private final MapCacheRepository mapCacheRepository;
    private final PriceCacheRepository priceCacheRepository;

    public CarRedisService(MapCacheRepository mapCacheRepository, PriceCacheRepository priceCacheRepository) {
        this.mapCacheRepository = mapCacheRepository;
        this.priceCacheRepository = priceCacheRepository;
    }

    public void savePrice(Price price) {
        priceCacheRepository.save(price);
    }

    public void saveAddress(Address address) {
        mapCacheRepository.save(address);

    }

    public Price findPrice(Long vehicleId) {
        return priceCacheRepository.findById(vehicleId).orElse(null);
    }

    public Address findAddress(double latitude, double longitude) {
        return mapCacheRepository.findById(latitude + "," + longitude).orElse(null);

    }

}

