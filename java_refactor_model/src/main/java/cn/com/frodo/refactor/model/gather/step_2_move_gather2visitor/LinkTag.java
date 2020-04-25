package cn.com.frodo.refactor.model.gather.step_2_move_gather2visitor;

public class LinkTag extends AbstractNode {

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitLinkTag(this);
    }

    public String getLinkText() {
        return null;
    }

    public boolean getLinks() {
        return false;
    }

    public Object getLink() {
        return null;
    }

}
