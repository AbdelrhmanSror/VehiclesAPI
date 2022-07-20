package com.sror.vehicles.api;


import com.sror.vehicles.sql.domain.Car;
import com.sror.vehicles.sql.service.CarSqlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Implements a REST-based controller for the Vehicles API.
 */
@RestController
@RequestMapping("/cars")
class CarController {

    private final CarSqlService carSqlService;


    public CarController(CarSqlService carSqlService) {
        this.carSqlService = carSqlService;
    }

    /**
     * @return list of vehicles
     */
    @GetMapping
    ResponseEntity<List<Car>> list() {
        return new ResponseEntity<>(carSqlService.list(), HttpStatus.OK);
    }

    /**
     * Gets information of a specific car by ID.
     *
     * @param id the id number of the given vehicle.
     * @return all information for the requested vehicle.
     */
    @GetMapping("/{id}")
    Car get(@PathVariable Long id) {
        return carSqlService.findById(id);
    }

    /**
     * Posts information to create a new vehicle in the system.
     *
     * @param car A new vehicle to add to the system.
     * @return response that the new vehicle was added to the system
     * @throws URISyntaxException if the request contains invalid fields or syntax
     */
    @PostMapping
    ResponseEntity<?> post(@Valid @RequestBody Car car) {
        return new ResponseEntity<>(carSqlService.save(car), HttpStatus.OK);
    }

    /**
     * Updates the information of a vehicle in the system.
     *
     * @param id  The ID number for which to update vehicle information.
     * @param car The updated information about the related vehicle.
     * @return response that the vehicle was updated in the system
     */
    @PutMapping("/{id}")
    ResponseEntity<?> put(@PathVariable Long id, @Valid @RequestBody Car car) {
        car.setId(id);
        return ResponseEntity.ok(carSqlService.save(car));
    }

    /**
     * Removes a vehicle from the system.
     *
     * @param id The ID number of the vehicle to remove.
     * @return response that the related vehicle is no longer in the system
     */
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable Long id) {
        carSqlService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
