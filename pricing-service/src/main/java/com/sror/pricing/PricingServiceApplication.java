package com.sror.pricing;

import com.sror.pricing.repository.PriceRepository;
import com.sror.pricing.service.PriceException;
import com.sror.pricing.service.PricingUtility;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.stream.LongStream;

/**
 * Creates a Spring Boot Application to run the Pricing Service.
 */
@SpringBootApplication
@EnableEurekaClient
public class PricingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PricingServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(PriceRepository repository) {

        return (args -> {
            LongStream.range(1, 6).forEach(number -> {
                try {
                    repository.save(PricingUtility.getPrice(number));

                } catch (PriceException e) {
                    e.printStackTrace();
                }
            });
        });
    }

}
