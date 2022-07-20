package com.sror.vehicles.sql.client.prices;

import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import com.sror.vehicles.redis.model.Price;
import com.sror.vehicles.redis.service.CarRedisService;
import com.sror.vehicles.utility.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

/**
 * Implements a class to interface with the Pricing Client for price data.
 */
@Component
public class PriceClient {

    private static final Logger log = LoggerFactory.getLogger(PriceClient.class);


    private static final String serviceName = "pricing-service";

    private final PeerAwareInstanceRegistry registry;
    /**
     * for calling cache services to check first existence of values
     * otherwise we make the call to actual service (maps_api,price_api)
     */
    private final CarRedisService carRedisService;

    public PriceClient(PeerAwareInstanceRegistry registry, CarRedisService carRedisService) {
        this.registry = registry;
        this.carRedisService = carRedisService;
    }


    // In a real-world application we'll want to add some resilience
    // to this method with retries/CB/failover capabilities
    // We may also want to cache the results, so we don't need to
    // do a request every time

    /**
     * Gets a vehicle price from the pricing client, given vehicle ID.
     *
     * @param vehicleId ID number of the vehicle for which to get the price
     * @return Currency and price of the requested vehicle,
     * error message that the vehicle ID is invalid, or note that the
     * service is down.
     */
    public String getPrice(Long vehicleId) {
        Price priceFromCache = carRedisService.findPrice(vehicleId);
        if (priceFromCache != null) {
            System.out.println("retrieving price from cache");
            return String.format("%s %s", priceFromCache.getCurrency(), priceFromCache.getPrice());
        }
        try {
            WebClient client = WebClient.create(Objects.requireNonNull(Utility.getEurekaClientUrl(registry, serviceName)));
            Price price = client
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path("services/price/")
                            .queryParam("vehicleId", vehicleId)
                            .build()
                    )
                    .retrieve().bodyToMono(Price.class).block();
            System.out.println("retrieving price from server");
            carRedisService.savePrice(price);
            return String.format("%s %s", price.getCurrency(), price.getPrice());

        } catch (Exception e) {
            log.error("Unexpected error retrieving price for vehicle {}", vehicleId, e);
        }
        return "(consult price)";
    }
}
