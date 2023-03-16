package cn.com.frodo.algorithm.leetcode;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE;

/**
 * 重点算法
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, MODULE, PARAMETER, TYPE})
public @interface LCPoint {

    Difficulty difficulty() default Difficulty.easy;

    Category category() default Category.tree;

    Company company() default Company.bytedance;

    enum Difficulty {
        easy, medium, hard
    }

    enum Category {
        _byte, array, linklist, stack, tree, graph
    }

    enum Company {
        bytedance
    }
}
