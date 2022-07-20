package com.sror.vehicles.utility;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import com.sror.vehicles.sql.domain.Condition;
import com.sror.vehicles.sql.domain.Location;
import com.sror.vehicles.sql.domain.Car;
import com.sror.vehicles.sql.domain.Details;
import com.sror.vehicles.sql.domain.Manufacturer;

import java.util.ArrayList;
import java.util.List;

public class Utility {
    public static String getEurekaClientUrl(PeerAwareInstanceRegistry registry, String name) {
        Application application = registry.getApplications().getRegisteredApplications(name);
        if (application != null) {
            InstanceInfo client = application.getInstancesAsIsFromEureka().get(0);
            return client.getHomePageUrl();
        }
        return null;
    }

    public static List<Manufacturer> getListOfManufacturer() {
        List<Manufacturer> manufacturers = new ArrayList<>();
        manufacturers.add(MANUFACTURERS[0]);
        manufacturers.add(MANUFACTURERS[1]);
        manufacturers.add(MANUFACTURERS[2]);
        manufacturers.add(MANUFACTURERS[3]);
        manufacturers.add(MANUFACTURERS[4]);
        return manufacturers;
    }

    public static List<Car> getListOfCars() {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Car car = new Car();
            car.setLocation(LOCATIONS[i]);
            car.setDetails(listOfDetails().get(i));
            car.setCondition(Condition.USED);
            cars.add(car);
        }

        return cars;
    }

    //dummy details of car
    private static List<Details> listOfDetails() {
        List<Details> details = new ArrayList<>();

        details.add(new Details("SEDAN", "Impala", MANUFACTURERS[1], 4, "Gasoline", "3.6L V6", 32280, 2018, 2018, "white"));
        details.add(new Details("COUPE", "A5 Coupe", MANUFACTURERS[0], 2, "Gasoline", "4.6L V8", 60000, 2021, 2021, "black"));
        details.add(new Details("SPORTS ", "GT", MANUFACTURERS[2], 2, "Gasoline", "6.6L V9", 80000, 2015, 2015, "red"));
        details.add(new Details("HATCHBACK", "BMW 1 Series", MANUFACTURERS[3], 4, "Gasoline", "4.2L V6", 586485, 2017, 2017, "yellow"));
        details.add(new Details("STATION WAGON", "MAGNUM", MANUFACTURERS[4], 4, "Gasoline", "2.6L V6", 258876, 1970, 1970, "white"));
        return details;
    }

    private static final Location[] LOCATIONS = {
            new Location(42.096298, -70.968498),
            new Location(42.121185, -71.030151),
            new Location(42.119202, -71.466270),
            new Location(42.098141, -71.056488),
            new Location(42.616550, -71.363342),
    };
    private static final Manufacturer[] MANUFACTURERS = {
            new Manufacturer(100, "Audi"),
            new Manufacturer(101, "Chevrolet"),
            new Manufacturer(102, "Ford"),
            new Manufacturer(103, "BMW"),
            new Manufacturer(104, "Dodge")
    };


}
