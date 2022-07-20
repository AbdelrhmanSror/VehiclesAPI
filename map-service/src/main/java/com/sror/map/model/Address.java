package com.sror.map.model;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Declares a class to store an address, city, state and zip code.
 */

@Entity
@Table(name = "address")
@NoArgsConstructor
public class Address {

    @EmbeddedId
    private Coordination coordination;
    @Column
    private String address;
    @Column
    private String city;
    @Column
    private String state;
    @Column
    private String zip;

    public Address(Coordination coordination, String address, String city, String state, String zip) {
        this.coordination = coordination;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public Coordination getCoordination() {
        return coordination;
    }

    public void setCoordination(Coordination coordination) {
        this.coordination = coordination;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return "Address{" +
                "address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                '}';
    }
}
