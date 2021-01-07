package com.mountain.chat.service.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfig {

    @Bean(name = "hikariDataSource0")
    @ConfigurationProperties(prefix = "chat.datasource.ds0")
    public HikariDataSource getDs0() {
        return new HikariDataSource();
    }

    @Bean(name = "hikariDataSource1")
    @ConfigurationProperties(prefix = "chat.datasource.ds1")
    public HikariDataSource getDs1() {
        return new HikariDataSource();
    }

}