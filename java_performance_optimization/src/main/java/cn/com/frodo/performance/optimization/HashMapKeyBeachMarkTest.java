package cn.com.frodo.performance.optimization;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@State(Scope.Benchmark)
@Warmup(iterations = 3, time = 3)
@Measurement(iterations = 5, time = 3)
public class HashMapKeyBeachMarkTest {
    private int size = 1024;
    private Map<String, Object> stringMap;
    private Map<Pair<String, String>, Object> pairMap;
    private String[] prefixes;
    private String[] suffixes;

    @Setup(Level.Trial)
    public void setup() {
        prefixes = new String[size];
        suffixes = new String[size];
        stringMap = new HashMap<>();
        pairMap = new HashMap<>();
        for (int i = 0; i < size; ++i) {
            prefixes[i] = UUID.randomUUID().toString();
            suffixes[i] = UUID.randomUUID().toString();
//            stringMap.put(prefixes[i] + ";" + suffixes[i], i);
            // use new String to avoid reference equality speeding up the equals calls
//            pairMap.put(MutablePair.of(prefixes[i], suffixes[i]), i);
        }
    }



    @Benchmark
    @OperationsPerInvocation(1024)
    public void stringKey(Blackhole bh) {
        for (int i = 0; i < prefixes.length; i++) {
//            bh.consume(stringMap.get(prefixes[i] + ";" + suffixes[i]));
            bh.consume(stringMap.put(prefixes[i] + ";" + suffixes[i], i));
        }
    }

    @Benchmark
    @OperationsPerInvocation(1024)
    public void pairMap(Blackhole bh) {
        for (int i = 0; i < prefixes.length; i++) {
//            bh.consume(pairMap.get(MutablePair.of(prefixes[i], suffixes[i])));
            bh.consume(pairMap.put(MutablePair.of(prefixes[i], suffixes[i]), i));
        }
    }
}
