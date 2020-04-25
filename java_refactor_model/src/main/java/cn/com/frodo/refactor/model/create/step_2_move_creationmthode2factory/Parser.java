package cn.com.frodo.refactor.model.create.step_2_move_creationmthode2factory;

public class Parser {
    private NodeFactory factory = new NodeFactory();

    public NodeFactory getFactory() {
        return factory;
    }

    public void setFactory(NodeFactory factory) {
        this.factory = factory;
    }

}
