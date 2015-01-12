package cn.com.frodo.refactor.model.simplify.step_3_move_decoration_2_decorator;

public abstract class AbstractNode implements Node {

	@Override
	public String toHtml() {
		return null;
	}

	@Override
	public String toPlainTextString() {
		return null;
	}

	@Override
	public void collectInto(NodeList nodes, String filter) {
	}

	@Override
	public void collectInto(NodeList nodes, Class nodeType) {
	}

	@Override
	public int elementBegin() {
		return 0;
	}

	@Override
	public int elementEnd() {
		return 0;
	}

	@Override
	public void accept(Node visitor) {
	}

	@Override
	public CompositeTag getParent() {
		return null;
	}

	@Override
	public void setParent(CompositeTag tag) {
	}
}
