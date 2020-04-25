package cn.com.frodo.refactor.model.create.step_2_move_creationmthode2factory;

public class StringParser {

    // FIXME 原始做法
    public Node find(StringBuffer textBuffer, int textBegin, int textEnd, Parser parser) {
        NodeFactory nodeFactory = new NodeFactory();
        return nodeFactory.createStringNode(textBuffer, textBegin, textEnd, parser.getFactory().isDecodeStringNodes());
    }

    // TODO 重构后
    public Node find2(StringBuffer textBuffer, int textBegin, int textEnd, Parser parser) {
        return parser.getFactory().createStringNode(textBuffer, textBegin, textEnd);
    }

}
