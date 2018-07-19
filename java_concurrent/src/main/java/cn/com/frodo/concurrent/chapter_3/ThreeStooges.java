package cn.com.frodo.concurrent.chapter_3;

import cn.com.frodo.concurrent.annotations.Immutable;

import java.util.HashSet;
import java.util.Set;

/**
 * 在可变对象基础构建的不可变类
 * Created by frodoking on 2015/1/13.
 */
@Immutable
public final class ThreeStooges {
    private final Set<String> stooges = new HashSet<String>();

    /**
     * 构造函数能使该引用(set集合)除了构造函数及其调用者之外的代码来访问
     */
    public ThreeStooges() {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name);
    }
}
