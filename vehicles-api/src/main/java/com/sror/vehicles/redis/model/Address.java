package com.sror.vehicles.redis.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

/**
 * Declares a class to store an address, city, state and zip code.
 */
@RedisHash(value = "Address", timeToLive = 30)
@NoArgsConstructor
@Data
public class Address implements Serializable {

    @Id
    private String compositeLatLong;
    private String address;
    private String city;
    private String state;
    private String zip;


    public Address(double latitude, double longitude, String address, String city, String state, String zip) {
        this.compositeLatLong = latitude + "," + longitude;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

}
