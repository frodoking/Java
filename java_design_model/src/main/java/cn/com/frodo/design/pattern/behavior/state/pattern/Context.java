package cn.com.frodo.design.pattern.behavior.state.pattern;

public class Context {
    public static State state1 = new ConcreteState1();
    public static State state2 = new ConcreteState1();

    private State currentState;

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
        currentState.setContext(this);
    }

    public void handle1() {
        setCurrentState(state1);
        currentState.handle();
    }

    public void handle2() {
        setCurrentState(state2);
        currentState.handle();
    }
}
