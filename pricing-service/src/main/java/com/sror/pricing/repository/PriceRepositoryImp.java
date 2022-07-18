package com.sror.pricing.repository;

import com.sror.pricing.model.Price;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PriceRepositoryImp implements PriceRepository {
    private RedisTemplate<Long, Price> redisTemplate;

    private HashOperations hashOperations;

    public PriceRepositoryImp(RedisTemplate<Long, Price> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(Long vehicleId, Price price) {
        hashOperations.put("Price", vehicleId, price);
    }

    @Override
    public Price findById(Long vehicleId) {
        return (Price) hashOperations.get("Price", vehicleId);
    }
}
