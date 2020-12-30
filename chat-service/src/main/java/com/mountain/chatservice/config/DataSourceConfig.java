package com.mountain.chatservice.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean(name = "hikariDataSource01")
    @ConfigurationProperties(prefix = "chat.datasource.ds0")
    public HikariDataSource getDs01() {
        return new HikariDataSource();
    }

}