package com.sror.boogle.maps.repository;

import com.sror.boogle.maps.model.Address;
import com.sror.boogle.maps.model.Coordination;

public interface AddressCoordinationFunction {
    void run(Coordination coordination, Address address);
}
