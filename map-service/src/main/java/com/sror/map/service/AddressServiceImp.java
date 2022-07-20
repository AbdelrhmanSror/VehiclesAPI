package com.sror.map.service;

import com.sror.map.common.exception.AddressException;
import com.sror.map.model.Address;
import com.sror.map.model.Coordination;
import com.sror.map.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AddressServiceImp implements AddressService {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Address findById(Coordination coordination) throws AddressException {
        return addressRepository.findById(coordination).orElseThrow(()->new AddressException("address not found"));

    }

    @Override
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public void save(Address address) {
        addressRepository.save(address);
    }
}
