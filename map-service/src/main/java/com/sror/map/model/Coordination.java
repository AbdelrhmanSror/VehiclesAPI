package com.sror.map.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class Coordination implements Serializable {

    @Column
    private double latitude;
    @Column
    private double longitude;

    public Coordination(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
