package cn.com.frodo.refactor.model.gather.step_2_move_gather2visitor;

public class StringNode extends AbstractNode {
    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitStringNode(this);
    }
}
