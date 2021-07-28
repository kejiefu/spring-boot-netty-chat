package com.mountain.im.connector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author kejiefu
 * @Classname
 * @Description
 * @Date 2020/9/19 11:18
 */
@SpringBootApplication
@EnableScheduling
public class ImConnectorApplication {

    private static final Logger logger = LoggerFactory.getLogger(ImConnectorApplication.class);

    public static void main(String[] args) {
        logger.info("~~~~~~~~~~~~~~~~ program start~~~~~~~~~~~~~~~~!");
        SpringApplication.run(ImConnectorApplication.class, args);
        logger.info("~~~~~~~~~~~~~~~~ program execute successfully~~~~~~~~~~~~~~~~!");
    }

}
