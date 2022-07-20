package com.sror.map.repository;

import com.sror.map.model.Address;
import com.sror.map.model.Coordination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Coordination> {
}
