package cn.com.frodo.design.pattern.creational.prototype.pattern;

public class ConcretePrototype implements Prototype {
    public Prototype clone() {
        try {
            return (Prototype) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
