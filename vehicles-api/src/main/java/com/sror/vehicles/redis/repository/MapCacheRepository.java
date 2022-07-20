package com.sror.vehicles.redis.repository;

import com.sror.vehicles.redis.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapCacheRepository extends JpaRepository<Address,String> {
}
