package cn.com.frodo.design.pattern.behavior.visitor.pattern;

import java.util.Random;
import java.util.Vector;

public class ObjectStructure {
    private Vector<Element> elements;

    public ObjectStructure() {
        elements = new Vector<Element>();
    }

    public void action(Visitor v) {
        for (Element element : elements) {
            element.accept(v);
        }
    }

    public void addElement(Element e) {
        elements.add(e);
    }

    public void createElements() {
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            if (r.nextInt(100) > 50) {
                addElement(new ConcreteElement1());
            } else {
                addElement(new ConcreteElement2());
            }
        }
    }
}
