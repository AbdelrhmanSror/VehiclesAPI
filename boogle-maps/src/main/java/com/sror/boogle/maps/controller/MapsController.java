package com.sror.boogle.maps.controller;

import com.sror.boogle.maps.model.Address;
import com.sror.boogle.maps.model.Coordination;
import com.sror.boogle.maps.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/maps")
public class MapsController {

    private AddressRepository addressRepository;

    @Autowired
    private void setAddressRepository(AddressRepository addressRepository){this.addressRepository=addressRepository;}

    @GetMapping
    public ResponseEntity<Address> get(@RequestParam Double lat, @RequestParam Double lon) {
        return new ResponseEntity<>(addressRepository.findById(new Coordination(lat,lon)), HttpStatus.OK);
    }
}
