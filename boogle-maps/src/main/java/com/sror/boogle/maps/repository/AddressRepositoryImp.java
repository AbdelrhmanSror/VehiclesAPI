package com.sror.boogle.maps.repository;

import com.sror.boogle.maps.model.Address;
import com.sror.boogle.maps.model.Coordination;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepositoryImp implements AddressRepository {

    private RedisTemplate<Coordination, Address> redisTemplate;

    private HashOperations hashOperations;

    public AddressRepositoryImp(RedisTemplate<Coordination, Address> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public void save(Coordination coordination, Address address) {
        hashOperations.put("Address", coordination, address);
    }

    @Override
    public Address findById(Coordination coordination) {
        return (Address) hashOperations.get("Address", coordination);

    }
}
