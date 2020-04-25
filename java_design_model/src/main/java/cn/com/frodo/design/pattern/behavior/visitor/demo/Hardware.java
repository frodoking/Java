package cn.com.frodo.design.pattern.behavior.visitor.demo;

public abstract class Hardware {
    String type;

    public Hardware(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public abstract void run();

    public abstract void accept(ComputerVisitor computerVisitor);
}
