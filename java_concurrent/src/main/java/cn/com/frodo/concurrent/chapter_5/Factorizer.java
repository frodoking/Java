package cn.com.frodo.concurrent.chapter_5;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * 在因式分解servlet中使用Memoizer来缓存结果
 * Created by frodo on 2015/1/15.
 */
public class Factorizer extends GenericServlet implements Servlet {

    private final Computable<BigInteger, BigInteger[]> c = new Computable<BigInteger, BigInteger[]>() {
        @Override
        public BigInteger[] compute(BigInteger arg) throws InterruptedException {
            return factor(arg);
        }
    };

    private final Computable<BigInteger, BigInteger[]> cache = new Memoizer<BigInteger, BigInteger[]>(c);

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        try {
            BigInteger i = extractFromRequest(servletRequest);
            encodeIntoResponse(servletResponse, cache.compute(i));
        } catch (InterruptedException e) {
            encodeError(servletResponse, "factorization interrupted");
        }
    }

    private void encodeError(ServletResponse response, String errorString) {
    }

    private void encodeIntoResponse(ServletResponse response, BigInteger[] factors) {
    }

    private BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[]{i};
    }

    private BigInteger extractFromRequest(ServletRequest request) {
        return new BigInteger("7");
    }
}
