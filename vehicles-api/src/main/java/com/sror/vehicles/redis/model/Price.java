package com.sror.vehicles.redis.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Represents the price of a given vehicle, including currency.
 */
@RedisHash(value = "Price", timeToLive = 30)
@Data
@NoArgsConstructor
public class Price implements Serializable {

    @Id
    private Long vehicleId;
    private String currency;
    private BigDecimal price;

    public Price(String currency, @NotNull BigDecimal price, Long vehicleId) {
        this.currency = currency;
        this.price = price;
        this.vehicleId = vehicleId;
    }
}
