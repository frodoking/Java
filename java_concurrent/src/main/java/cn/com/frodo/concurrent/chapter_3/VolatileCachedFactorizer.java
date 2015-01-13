package cn.com.frodo.concurrent.chapter_3;

import cn.com.frodo.concurrent.annotation.ThreadSafe;

import java.math.BigInteger;

/**
 * 使用指向不可变对象的volatile类型引用以缓存最新的结果
 * Created by xuwei19 on 2015/1/13.
 */
@ThreadSafe
public class VolatileCachedFactorizer {
    private volatile OneValueCache cache = new OneValueCache(null, null);

    public void service(String args1, String args2) {
        BigInteger i = extractFromRequest(args1);
        BigInteger[] factors = cache.getFactors(i);
        if (factors == null) {
            factors = factor(i);
            cache = new OneValueCache(i, factors);
        }
        encodeIntoResponse(args2, factors);

    }

    private void encodeIntoResponse(String args2, BigInteger[] factors) {
    }

    private BigInteger extractFromRequest(String args1) {
        return null;
    }

    private BigInteger[] factor(BigInteger i) {
        return null;
    }
}
