package com.sror.pricing;

import com.sror.pricing.model.Price;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PricingControllerIntegrationTests {

        @LocalServerPort
        private int port;

        @Autowired
        private TestRestTemplate restTemplate;

        @Test
        public void getPrice() {
            ResponseEntity<Price> response =
                    this.restTemplate.getForEntity("http://localhost:" + port + "/services/price?vehicleId=3", Price.class);
            assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
            System.out.println(response.getBody());
        }



}
