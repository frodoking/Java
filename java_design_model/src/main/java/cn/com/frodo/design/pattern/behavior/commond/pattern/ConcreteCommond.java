package cn.com.frodo.design.pattern.behavior.commond.pattern;

public class ConcreteCommond implements Commond {
    private Receiver receiver;

    public ConcreteCommond(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.action();
    }
}
