package cn.com.frodo.performance.optimization;

import org.openjdk.jmh.annotations.*;

import java.util.HashMap;

@State(Scope.Benchmark)
@Warmup(iterations = 3,time = 3)
@Measurement(iterations = 5,time = 3)
public class HashMapSizeBeachMarkTest {

    @Param({"14"})
    int keys;

    @Param({"16", "32"})
    int size;

    @Benchmark
    public HashMap<Integer, Integer> getHashMap() {
        HashMap<Integer, Integer> map = new HashMap<>(size);
        for (int i = 0; i < keys; i++) {
            map.put(i, i);
        }
        return map;
    }
}
