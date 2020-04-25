package cn.com.frodo.refactor.model.gather.step_2_move_gather2visitor;

public class Tag extends AbstractNode {

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitTag(this);
    }

    public String getTagName() {
        return null;
    }

}
