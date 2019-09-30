package com.frodo.knowledge;

import com.google.common.base.Splitter;
import org.yaml.snakeyaml.Yaml;

import java.util.Iterator;
import java.util.LinkedHashMap;

public class Configuration
{
    private static final Object GLOBAL_CONFIG;

    static {
        Yaml yaml = new Yaml();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        GLOBAL_CONFIG = yaml.load(classloader.getResourceAsStream("config.yml"));
    }

    public static Object get(String key)
    {
        if (GLOBAL_CONFIG instanceof LinkedHashMap)
        {
            LinkedHashMap map = (LinkedHashMap) GLOBAL_CONFIG;
            Iterator<String> iterator = Splitter.on(".").split(key).iterator();
            Object result = map;
            while (iterator.hasNext())
            {
                String next = iterator.next();
                if (result instanceof LinkedHashMap)
                {
                    if (((LinkedHashMap) result).containsKey(next))
                    {
                        result = ((LinkedHashMap) result).get(next);
                    }
                }
                else
                {
                    result = null;
                }
            }
            return result;
        }
        return null;
    }
}
