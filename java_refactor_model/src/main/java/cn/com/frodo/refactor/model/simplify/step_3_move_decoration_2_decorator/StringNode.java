package cn.com.frodo.refactor.model.simplify.step_3_move_decoration_2_decorator;

public class StringNode extends AbstractNode {

	StringBuffer textBuffer;
	int textBegin;
	int textEnd;

	protected StringNode(StringBuffer textBuffer, int textBegin, int textEnd) {
		this.textBuffer = textBuffer;
		this.textBegin = textBegin;
		this.textEnd = textEnd;
	}

	public static Node createStringNode(StringBuffer textBuffer, int textBegin, int textEnd, boolean shouldDecode) {
		StringNode stringNode = new StringNode(textBuffer, textBegin, textEnd);
		return shouldDecode ? new DecodingNode(stringNode) : stringNode;
	}

	@Override
	public String toHtml() {
		return null;
	}

	@Override
	public String toPlainTextString() {
		return textBuffer.toString();
	}
}
