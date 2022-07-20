package com.sror.vehicles;

import com.sror.vehicles.sql.repository.CarRepository;
import com.sror.vehicles.sql.repository.ManufacturerRepository;
import com.sror.vehicles.utility.Utility;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;

/**
 * Launches a Spring Boot application for the Vehicles API,
 * initializes the car manufacturers in the database,
 * and launches web clients to communicate with maps and pricing.
 */
@SpringBootApplication
@EnableEurekaServer
public class VehiclesApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehiclesApiApplication.class, args);
    }




    /**
     * Initializes the car manufacturers available to the Vehicle API.
     *
     * @param repository where the manufacturer information persists.
     * @return the car manufacturers to add to the related repository
     */
    @Bean
    CommandLineRunner initDatabase(ManufacturerRepository repository, CarRepository carRepository) {
        return args -> {
            Utility.getListOfManufacturer().forEach(repository::save);
            Utility.getListOfCars().forEach(carRepository::save);
        };
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
