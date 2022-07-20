package com.sror.vehicles.sql.client.maps;

import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import com.sror.vehicles.redis.model.Address;
import com.sror.vehicles.redis.service.CarRedisService;
import com.sror.vehicles.sql.domain.Location;
import com.sror.vehicles.utility.Utility;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

/**
 * Implements a class to interface with the Maps Client for location data.
 */
@Component
public class MapsClient {

    private static final Logger log = LoggerFactory.getLogger(MapsClient.class);
    /**
     * for calling cache services to check first existence of values
     * otherwise we make the call to actual service (maps_api,price_api)
     */
    private final CarRedisService carRedisService;


    private final ModelMapper mapper;
    private static final String serviceName = "maps-service";

    private final PeerAwareInstanceRegistry registry;

    public MapsClient(CarRedisService carRedisService, ModelMapper mapper, PeerAwareInstanceRegistry registry) {
        this.carRedisService = carRedisService;
        this.mapper = mapper;
        this.registry = registry;
    }

    /**
     * Gets an address from the Maps client, given latitude and longitude.
     *
     * @param location An object containing "lat" and "lon" of location
     * @return An updated location including street, city, state and zip,
     * or an exception message noting the Maps service is down
     */
    public Location getAddress(Location location) {
        Address addressFromCache = carRedisService.findAddress(location.getLat(), location.getLon());
        if (addressFromCache != null) {
            mapper.map(Objects.requireNonNull(addressFromCache), location);
            System.out.println("retrieving address from cache");
            System.out.println(addressFromCache);
            return location;
        }
        try {
            WebClient client = WebClient.create(Objects.requireNonNull(Utility.getEurekaClientUrl(registry, serviceName)));
            Address address = client
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/maps/")
                            .queryParam("lat", location.getLat())
                            .queryParam("lon", location.getLon())
                            .build()
                    )
                    .retrieve().bodyToMono(Address.class).block();

            mapper.map(Objects.requireNonNull(address), location);
            address.setCompositeLatLong(location.getLat()+","+location.getLon());
            carRedisService.saveAddress(address);
            System.out.println("retrieving address from server");



            return location;
        } catch (Exception e) {
            log.warn("Map service is down");
            return location;
        }
    }
}
