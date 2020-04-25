package com.frodo.spi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SPIQueen implements SPIInterface {

    private static final Logger LOGGER = LoggerFactory.getLogger(SPIQueen.class);

    @Override
    public void executeMethodOne() {
        LOGGER.info(">> {}, hashcode: {}", "executeMethodOne", hashCode());
    }

    @Override
    public void executeMethodTwo() {
        LOGGER.info(">> {}, hashcode: {}", "executeMethodTwo", hashCode());
    }
}
