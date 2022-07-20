package com.sror.vehicles.sql.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Declares the additional detail variables for each Car object,
 * along with related methods for access and setting.
 */
@Embeddable
public class Details {

    @NotBlank
    @Column
    private String body;

    @NotBlank
    @Column
    private String model;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "code")
    private Manufacturer manufacturer;

    @Column
    private Integer numberOfDoors;
    @Column
    private String fuelType;

    @Column
    private String engine;

    @Column
    private Integer mileage;

    @Column
    private Integer modelYear;
    @Column
    private Integer productionYear;
    @Column
    private String externalColor;

    public Details() {
    }

    public Details(String body, String model, Manufacturer manufacturer, Integer numberOfDoors, String fuelType, String engine, Integer mileage, Integer modelYear, Integer productionYear, String externalColor) {
        this.body = body;
        this.model = model;
        this.manufacturer = manufacturer;
        this.numberOfDoors = numberOfDoors;
        this.fuelType = fuelType;
        this.engine = engine;
        this.mileage = mileage;
        this.modelYear = modelYear;
        this.productionYear = productionYear;
        this.externalColor = externalColor;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Integer getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(Integer numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Integer getModelYear() {
        return modelYear;
    }

    public void setModelYear(Integer modelYear) {
        this.modelYear = modelYear;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public String getExternalColor() {
        return externalColor;
    }

    public void setExternalColor(String externalColor) {
        this.externalColor = externalColor;
    }

    @Override
    public String toString() {
        return "Details{" +
                "body='" + body + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer=" + manufacturer +
                ", numberOfDoors=" + numberOfDoors +
                ", fuelType='" + fuelType + '\'' +
                ", engine='" + engine + '\'' +
                ", mileage=" + mileage +
                ", modelYear=" + modelYear +
                ", productionYear=" + productionYear +
                ", externalColor='" + externalColor + '\'' +
                '}';
    }
}
