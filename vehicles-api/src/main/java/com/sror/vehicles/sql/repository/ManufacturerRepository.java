package com.sror.vehicles.sql.repository;

import com.sror.vehicles.sql.domain.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Integer> {
    @Modifying
    @Transactional
    @Query(value = "drop table Manufacturer", nativeQuery = true)
    void dropTable();

}
