package com.sror.pricing;

import com.sror.pricing.model.Price;
import com.sror.pricing.repository.PriceRepository;
import com.sror.pricing.service.PriceException;
import com.sror.pricing.service.PricingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
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
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    RedisTemplate<Long, Price> redisTemplate() {
        RedisTemplate<Long, Price> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

    @Bean
    public CommandLineRunner demo(PriceRepository repository) {

        return (args -> {
            LongStream.range(1, 20).forEach(number -> {
                try {
                    repository.save(number, PricingService.getPrice(number));
                } catch (PriceException e) {
                    e.printStackTrace();
                }
            });
        });
    }

}
