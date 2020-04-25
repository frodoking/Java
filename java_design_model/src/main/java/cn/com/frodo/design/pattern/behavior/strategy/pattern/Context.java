package cn.com.frodo.design.pattern.behavior.strategy.pattern;

public class Context {
    private Strategy strategy = null;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void contextInterface() {
        strategy.strategyInterface();
    }
}
