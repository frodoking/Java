package cn.com.frodo.refactor.model.create.step_2_move_creationmthode2factory;

import cn.com.frodo.refactor.model.create.step_2_move_creationmthode2factory.StringNode.DecodingStringNode;

public class NodeFactory {
    private boolean decodeStringNodes;

    public boolean isDecodeStringNodes() {
        return decodeStringNodes;
    }

    public void setDecodeStringNodes(boolean decodeStringNodes) {
        this.decodeStringNodes = decodeStringNodes;
    }

    public Node createStringNode(StringBuffer textBuffer, int textBegin, int textEnd) {
        if (decodeStringNodes)
            return new DecodingStringNode(new StringNode(textBuffer, textBegin, textEnd));

        return new StringNode(textBuffer, textBegin, textEnd);
    }

    //FIXME old
    public Node createStringNode(StringBuffer textBuffer, int textBegin, int textEnd, boolean shouldDecode) {
        if (shouldDecode)
            return new DecodingStringNode(new StringNode(textBuffer, textBegin, textEnd));

        return new StringNode(textBuffer, textBegin, textEnd);
    }
}
