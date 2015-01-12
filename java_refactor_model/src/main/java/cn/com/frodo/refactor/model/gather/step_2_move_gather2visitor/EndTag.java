package cn.com.frodo.refactor.model.gather.step_2_move_gather2visitor;

public class EndTag extends AbstractNode {

	@Override
	public void accept(NodeVisitor visitor) {
		visitor.visitEndTag(this);
	}

	public String getTagName() {
		return null;
	}

}
