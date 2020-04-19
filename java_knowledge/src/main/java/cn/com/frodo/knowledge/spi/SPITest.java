package cn.com.frodo.knowledge.spi;

import cn.com.frodo.MockInterface;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SPITest implements MockInterface
{
    @Override public void doTest()
    {
        ServiceLoader<com.frodo.spi.SPIInterface> spiInterfaces = ServiceLoader.load(com.frodo.spi.SPIInterface.class);
        Iterator<com.frodo.spi.SPIInterface> iterator  = spiInterfaces.iterator();
        while (iterator.hasNext()) {
            com.frodo.spi.SPIInterface spiInterface = iterator.next();
            spiInterface.executeMethodOne();
            spiInterface.executeMethodTwo();
        }
    }
}
