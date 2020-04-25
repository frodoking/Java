package cn.com.frodo.knowledge.btrace;

import com.sun.btrace.annotations.*;

import static com.sun.btrace.BTraceUtils.Strings.str;
import static com.sun.btrace.BTraceUtils.Strings.strcat;
import static com.sun.btrace.BTraceUtils.Threads.jstack;
import static com.sun.btrace.BTraceUtils.println;
import static com.sun.btrace.BTraceUtils.timeMillis;

@BTrace
public class TraceMethodArgsAndReturn {
    @OnMethod(clazz = "com.frodo.btrace.BTraceTest", method = "intern", location = @Location(Kind.RETURN))
    public static void traceExecute(int sSrc, @Return long result) {
        println("invoke com.frodo.btrace.BTraceTest.intern");
        println(strcat("input arg is:", str(sSrc)));

        println(strcat("result is:", str(result)));

        println(strcat("time is:", str(timeMillis())));

        jstack();
    }
}
