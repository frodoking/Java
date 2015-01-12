package cn.com.frodo.refactor.model.simplify.step_3_move_decoration_2_decorator;

public class StringParser extends Parser {

	public Node find(NodeReader reader, String input, int position, boolean balance_quotes) {
		return StringNode.createStringNode(null, 0, 10, reader.getParser().shouldDecodeNodes());
	}

}
