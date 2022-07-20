package com.sror.map;

import com.sror.map.common.AddressUtility;
import com.sror.map.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableEurekaClient
public class MapsApplication {
    private static final Logger log = LoggerFactory.getLogger(MapsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MapsApplication.class, args);
    }


    @Bean
    public CommandLineRunner demo(AddressService addressService) {
        return (args -> {
            AddressUtility.getListOfAddress().forEach(addressService::save);
        });
    }
}
