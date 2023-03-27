package cn.com.frodo.algorithm;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE})
public @interface Algorithm {

    AlgorithmEnum[] value() default {AlgorithmEnum.force};

    enum AlgorithmEnum {
        force, dp, twoPointers, monotonicStack,
    }
}
