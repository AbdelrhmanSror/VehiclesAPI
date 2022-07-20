package com.sror.map.controller;

import com.sror.map.model.Address;
import com.sror.map.model.Coordination;
import com.sror.map.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/maps")
public class MapsController {


    private AddressService addressService;


    public MapsController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<Address> get(@RequestParam Double lat, @RequestParam Double lon) throws Exception {
        return new ResponseEntity<>(addressService.findById(new Coordination(lat, lon)), HttpStatus.OK);

    }

    @GetMapping("/Addresses")
    public ResponseEntity<List<Address>> getAll() {
        return new ResponseEntity<>(addressService.findAll(), HttpStatus.OK);

    }
}
