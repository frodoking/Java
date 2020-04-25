package com.frodo.spi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SPILoard implements SPIInterface {
    private static final Logger LOGGER = LoggerFactory.getLogger(SPILoard.class);

    @Override
    public void executeMethodOne() {
        LOGGER.info(">> {}, hashcode: {}", "executeMethodOne", hashCode());
    }

    @Override
    public void executeMethodTwo() {
        LOGGER.info(">> {}, hashcode: {}", "executeMethodTwo", hashCode());
    }
}
