package com.mountain.im.transfer.config;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Redisson配置
 *
 * @author shaozj
 * @since 2020/4/20 19:57
 */
@Configuration
@Slf4j
public class RedissonConfig {

    @Resource
    RedisProperties redisProperties;

    /**
     * 单机模式
     */
    @Bean
    public RedissonClient redissonSingle() {
        Config config = new Config();
        String address = "redis://" + redisProperties.getHost() + ":" + redisProperties.getPort();
        config.useSingleServer().setAddress(address);
        config.useSingleServer().setPassword(redisProperties.getPassword());
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }


}
