package cn.com.frodo.design.pattern.structual.decorator.pattern;

public abstract class Decorator implements Component {
    private Component component = null;

    public Decorator(Component component) {
        this.component = component;
    }

    @Override
    public void operation() {
        this.component.operation();
    }
}
