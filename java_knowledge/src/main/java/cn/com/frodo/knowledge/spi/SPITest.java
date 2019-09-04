package com.frodo.spi;

import com.frodo.Test;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SPITest implements Test
{
    @Override public void doTest()
    {
        ServiceLoader<SPIInterface> spiInterfaces = ServiceLoader.load(SPIInterface.class);
        Iterator<SPIInterface> iterator  = spiInterfaces.iterator();
        while (iterator.hasNext()) {
            SPIInterface spiInterface = iterator.next();
            spiInterface.executeMethodOne();
            spiInterface.executeMethodTwo();
        }
    }
}
