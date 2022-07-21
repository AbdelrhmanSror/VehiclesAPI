package com.sror.vehicles.api;

import com.sror.vehicles.sql.domain.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Implements testing of the CarController class.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void getCar() {
        ResponseEntity<Car> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/cars/1", Car.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        System.out.println(response.getBody());
    }

    @Test
    public void getAllCars() {
        ResponseEntity<Object> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/cars", Object.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        for (Object address : ((List<Object>) response.getBody())) {
            System.out.println(address);

        }

    }



}