package com.sror.vehicles.api;


import com.sror.vehicles.sql.domain.Car;
import com.sror.vehicles.sql.service.CarService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Implements a REST-based controller for the Vehicles API.
 */
@RestController
@RequestMapping("/cars")
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "this a bad request please review api documentation for more details"),
        @ApiResponse(responseCode = "401", description = "due to security constraints ,your access request can not be authorized"),
        @ApiResponse(responseCode = "500", description = "the server is down please make sure the car server is up and running")})
class CarController {

    private final CarService carService;


    public CarController(CarService carService) {
        this.carService = carService;
    }

    /**
     * @return list of vehicles
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Car> list() {
        return carService.list();
    }

    /**
     * Gets information of a specific car by ID.
     *
     * @param id the id number of the given vehicle.
     * @return all information for the requested vehicle.
     */
    @GetMapping("/{id}")
    Car get(@PathVariable Long id) {
        return carService.findById(id);
    }

    /**
     * Posts information to create a new vehicle in the system.
     *
     * @param car A new vehicle to add to the system.
     * @return response that the new vehicle was added to the system
     * @throws URISyntaxException if the request contains invalid fields or syntax
     */
    @PostMapping
    @ResponseStatus(value = HttpStatus.OK, reason = "car has been inserted")
    Car post(@Valid @RequestBody Car car) {
        return carService.save(car);
    }

    /**
     * Updates the information of a vehicle in the system.
     *
     * @param id  The ID number for which to update vehicle information.
     * @param car The updated information about the related vehicle.
     * @return response that the vehicle was updated in the system
     */
    @PutMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "car has been updated")
    Car put(@PathVariable Long id, @Valid @RequestBody Car car) {
        car.setId(id);
        return carService.save(car);
    }

    /**
     * Removes a vehicle from the system.
     *
     * @param id The ID number of the vehicle to remove.
     * @return response that the related vehicle is no longer in the system
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "car has been deleted")
    void delete(@PathVariable Long id) {
        carService.delete(id);
    }
}
