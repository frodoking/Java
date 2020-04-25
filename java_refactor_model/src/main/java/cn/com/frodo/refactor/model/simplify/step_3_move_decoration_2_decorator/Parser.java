package cn.com.frodo.refactor.model.simplify.step_3_move_decoration_2_decorator;

public class Parser {
    boolean shouldDecodeNodes;

    public boolean shouldDecodeNodes() {
        return shouldDecodeNodes;
    }

    public void setShouldDecodeNodes(boolean shouldDecodeNodes) {
        this.shouldDecodeNodes = shouldDecodeNodes;
    }
}
