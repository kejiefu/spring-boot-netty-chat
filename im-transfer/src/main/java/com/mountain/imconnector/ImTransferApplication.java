package com.mountain.imconnector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author kejiefu
 * @Classname
 * @Description
 * @Date 2020/9/19 11:18
 */
@SpringBootApplication
public class ImTransferApplication {

    private static final Logger logger = LoggerFactory.getLogger(ImTransferApplication.class);

    public static void main(String[] args) {
        logger.info("~~~~~~~~~~~~~~~~ program start~~~~~~~~~~~~~~~~!");
        SpringApplication.run(ImTransferApplication.class, args);
        logger.info("~~~~~~~~~~~~~~~~ program execute successfully~~~~~~~~~~~~~~~~!");
    }

}