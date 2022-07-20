package com.sror.pricing.model;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * Represents the price of a given vehicle, including currency.
 * time to live 10 seconds  in memory cache (REDIS).
 */
//@RedisHash(value = "Price", timeToLive = 10)
@Entity
@Table(name = "price")
@NoArgsConstructor
public class Price {

    @Id
    private Long vehicleId;
    @Column
    private String currency;
    @Column
    private BigDecimal price;

    public Price(String currency, BigDecimal price, Long vehicleId) {
        this.currency = currency;
        this.price = price;
        this.vehicleId = vehicleId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Price{" +
                "currency='" + currency + '\'' +
                ", price=" + price +
                ", vehicleId=" + vehicleId +
                '}';
    }
}
