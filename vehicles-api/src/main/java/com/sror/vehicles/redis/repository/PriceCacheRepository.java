package com.sror.vehicles.redis.repository;

import com.sror.vehicles.redis.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceCacheRepository extends JpaRepository<Price,Long> {
}
