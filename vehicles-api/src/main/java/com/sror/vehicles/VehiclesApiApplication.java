package com.sror.vehicles;

import com.sror.vehicles.domain.Condition;
import com.sror.vehicles.domain.Location;
import com.sror.vehicles.domain.car.Car;
import com.sror.vehicles.domain.car.CarRepository;
import com.sror.vehicles.domain.car.Details;
import com.sror.vehicles.domain.manufacturer.Manufacturer;
import com.sror.vehicles.domain.manufacturer.ManufacturerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Launches a Spring Boot application for the Vehicles API,
 * initializes the car manufacturers in the database,
 * and launches web clients to communicate with maps and pricing.
 */
@SpringBootApplication
@EnableJpaAuditing
@EnableEurekaServer
public class VehiclesApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VehiclesApiApplication.class, args);
    }

  /*  @Autowired
    private DiscoveryClient discoveryClient;
*/
    /**
     * Initializes the car manufacturers available to the Vehicle API.
     *
     * @param repository where the manufacturer information persists.
     * @return the car manufacturers to add to the related repository
     */
    @Bean
    CommandLineRunner initDatabase(ManufacturerRepository repository, CarRepository carRepository) {
        return args -> {
            Car car = new Car();
            car.setLocation(new Location(42.121185, -71.030151));
            Details details = new Details();
            Manufacturer manufacturer = new Manufacturer(101, "Chevrolet");
            details.setManufacturer(manufacturer);
            details.setModel("Impala");
            details.setMileage(32280);
            details.setExternalColor("white");
            details.setBody("sedan");
            details.setEngine("3.6L V6");
            details.setFuelType("Gasoline");
            details.setModelYear(2018);
            details.setProductionYear(2018);
            details.setNumberOfDoors(4);
            car.setDetails(details);
            car.setCondition(Condition.USED);
            repository.save(new Manufacturer(100, "Audi"));
            repository.save(new Manufacturer(101, "Chevrolet"));
            repository.save(new Manufacturer(102, "Ford"));
            repository.save(new Manufacturer(103, "BMW"));
            repository.save(new Manufacturer(104, "Dodge"));
            carRepository.save(car);
        };
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}
