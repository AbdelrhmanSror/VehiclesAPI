package com.sror.boogle.maps.repository;

import com.sror.boogle.maps.model.Address;
import com.sror.boogle.maps.model.Coordination;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface AddressRepository {
    void save(Coordination coordination,Address address);

    Address findById(Coordination coordination);
}
