package cn.com.frodo.refactor.model.simplify.step_3_move_decoration_2_decorator;


public interface Node {
    String toHtml();

    String toPlainTextString();

    String toString();

    void collectInto(NodeList nodes, String filter);

    void collectInto(NodeList nodes, Class nodeType);

    int elementBegin();

    int elementEnd();

    void accept(Node visitor);

    CompositeTag getParent();

    void setParent(CompositeTag tag);
}
