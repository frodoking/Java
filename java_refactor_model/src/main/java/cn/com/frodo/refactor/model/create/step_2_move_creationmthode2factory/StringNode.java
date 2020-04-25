package cn.com.frodo.refactor.model.create.step_2_move_creationmthode2factory;

/**
 * 将创建的方法移到factory当中
 *
 * @author frodoking
 */
public class StringNode extends Node {
    StringBuffer textBuffer;
    int textBegin;
    int textEnd;
    boolean shouldDecode;

    public StringNode(StringBuffer textBuffer, int textBegin, int textEnd) {
        this(textBuffer, textBegin, textEnd, true);
    }

    public StringNode(StringBuffer textBuffer, int textBegin, int textEnd, boolean shouldDecode) {
        this.textBuffer = textBuffer;
        this.textBegin = textBegin;
        this.textEnd = textEnd;
        this.shouldDecode = shouldDecode;
    }

    public static class DecodingStringNode extends Node {
        StringNode stringNode;

        public DecodingStringNode(StringNode stringNode) {
            super();
            this.stringNode = stringNode;
        }

    }
}
