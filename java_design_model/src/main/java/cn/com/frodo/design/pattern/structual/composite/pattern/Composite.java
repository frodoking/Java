package cn.com.frodo.design.pattern.structual.composite.pattern;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {
    private List<Component> components = new ArrayList<Component>();

    public void add(Component component) {
        components.add(component);
    }

    public void remove(int index) {
        components.remove(index);
    }

    public List<Component> getLst() {
        return components;
    }

    @Override
    public void operation() {

    }
}
