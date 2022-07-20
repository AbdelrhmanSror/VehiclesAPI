package com.sror.vehicles.redis.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@Log4j2
@EnableRedisRepositories(
        basePackages = "com.sror.vehicles.redis.repository"
)
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private Integer port;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        log.info("=================================================================");
        log.info("redis config : {} : {} ", host, port);
        log.info("=================================================================");

        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(host, port);
        return new JedisConnectionFactory(config);
    }
/*
    @Bean
    public RedisTemplate<Double, Price> redisTemplate(RedisConfig redisConfig) {
        RedisTemplate<Double, Price> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConfig.jedisConnectionFactory());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @Bean
    public RedisTemplate<Long, Address> redisTemplate1(RedisConfig redisConfig) {
        RedisTemplate<Long, Address> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConfig.jedisConnectionFactory());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }*/

}