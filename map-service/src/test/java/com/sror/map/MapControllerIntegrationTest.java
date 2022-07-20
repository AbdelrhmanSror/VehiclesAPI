package com.sror.map;

import com.sror.map.model.Address;
import com.sror.map.repository.AddressRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MapControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AddressRepository repository;

    @Test
    public void getLocationByLatAndLon() {
        ResponseEntity<Address> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/maps/?lat=42.121185&lon=-71.030151", Address.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        System.out.println(response.getBody());
    }

    @Test
    public void getAllLocations() {
        ResponseEntity<Object> response =
                this.restTemplate.getForEntity("http://localhost:" + port + "/maps/Addresses", Object.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        for (Object address : ((List<Object>) response.getBody())) {
            System.out.println(address);

        }

    }

}
