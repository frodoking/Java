package cn.com.frodo.design.pattern.structual.composite.pattern;

/**
 * 定义叶子构建
 *
 * @author frodoking
 */
public class Leaf implements Component {

    @Override
    public void operation() {
        System.out.println("------leaf---------");
    }
}
