package cn.com.frodo.algorithm;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE;

/**
 * 重点算法
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE})
public @interface AlgorithmPoint {

    Tag tag() default Tag.leetcode;

    Difficulty difficulty() default Difficulty.easy;

    Category category() default Category.array;

    Company[] company() default {Company.bytedance};

    enum Tag {
        leetcode, offer, interview
    }

    enum Difficulty {
        easy, medium, hard
    }

    enum Category {
        _byte, digit, array, matrix, linklist, stack, tree, graph, thread
    }

    enum Company {
        bytedance, tencent, ali, baidu, meituan, didi, xiaomi, xiaohongshu, huawei
    }
}
