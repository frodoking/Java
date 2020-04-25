package cn.com.frodo.design.pattern.creational.factorymethod.demo;

/**
 * 水果园
 *
 * @author frodoking
 */
public interface FruitGardener {
    /**
     * 产水果
     *
     * @return
     */
    public Fruit factory();
}
