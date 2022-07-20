package com.sror.vehicles.sql.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Declares class to hold car manufacturer information.
 */
@Entity
@Table
public class Manufacturer {

    @Id
    private Integer code;
    @Column
    private String name;

    public Manufacturer() { }

    public Manufacturer(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Manufacturer{" +
                "code=" + code +
                ", name='" + name + '\'' +
                '}';
    }
}
