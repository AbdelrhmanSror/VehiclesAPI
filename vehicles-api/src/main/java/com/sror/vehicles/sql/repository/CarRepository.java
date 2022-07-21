package com.sror.vehicles.sql.repository;

import com.sror.vehicles.sql.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query(value = "drop table Car", nativeQuery = true)
    void dropTable();
}
