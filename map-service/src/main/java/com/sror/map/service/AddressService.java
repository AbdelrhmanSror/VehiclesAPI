package com.sror.map.service;

import com.sror.map.model.Coordination;
import com.sror.map.model.Address;

import java.util.List;

public interface AddressService {

    Address findById(Coordination coordination) throws Exception;

    List<Address> findAll();

    void save(Address address);


}
