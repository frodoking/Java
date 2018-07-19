package cn.com.frodo.concurrent.chapter_3;

import cn.com.frodo.concurrent.annotations.Immutable;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 对数值及其因数分解结果进行缓存的不可变容器类
 * Created by frodoking on 2015/1/13.
 */
@Immutable
public class OneValueCache {
    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger i, BigInteger[] factors) {
        this.lastNumber = i;
        this.lastFactors = Arrays.copyOf(factors, factors.length);
    }

    public BigInteger[] getFactors(BigInteger i) {
        if (lastNumber == null || !lastNumber.equals(i))
            return null;
        else
            return Arrays.copyOf(lastFactors, lastFactors.length);
    }
}