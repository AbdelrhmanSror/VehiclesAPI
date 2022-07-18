package com.sror.boogle.maps;

import com.sror.boogle.maps.model.Address;
import com.sror.boogle.maps.model.Coordination;
import com.sror.boogle.maps.repository.AddressRepository;
import com.sror.boogle.maps.repository.AddressUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;


@SpringBootApplication
@EnableEurekaClient
public class BoogleMapsApplication {
    private static final Logger log = LoggerFactory.getLogger(BoogleMapsApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BoogleMapsApplication.class, args);
    }

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    RedisTemplate<Coordination, Address> redisTemplate() {
        RedisTemplate<Coordination, Address> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        return redisTemplate;
    }

    @Bean
    public CommandLineRunner demo(AddressRepository repository) {
        return (args -> {
            AddressUtility.insertBulkIntoRedis(repository::save);
        });
    }
}
