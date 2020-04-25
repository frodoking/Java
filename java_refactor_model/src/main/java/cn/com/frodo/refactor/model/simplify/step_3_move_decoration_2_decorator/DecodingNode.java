package cn.com.frodo.refactor.model.simplify.step_3_move_decoration_2_decorator;

/**
 * 典型的装饰器，对StringNode的完全装饰，然后实现单独不同点(完成自身的decode)
 *
 * @author frodoking
 */
public class DecodingNode implements Node {

    // 采用委托替换继承
    private Node delegate;

    public DecodingNode(Node delegate) {
        this.delegate = delegate;
    }

    @Override
    public String toPlainTextString() {
        return Translate.decode(delegate.toPlainTextString());
    }

    @Override
    public String toHtml() {
        return delegate.toHtml();
    }

    @Override
    public void collectInto(NodeList nodes, String filter) {
        delegate.collectInto(nodes, filter);
    }

    @Override
    public void collectInto(NodeList nodes, Class nodeType) {
        delegate.collectInto(nodes, nodeType);
    }

    @Override
    public int elementBegin() {
        return delegate.elementBegin();
    }

    @Override
    public int elementEnd() {
        return delegate.elementEnd();
    }

    @Override
    public void accept(Node visitor) {
        delegate.accept(visitor);
    }

    @Override
    public CompositeTag getParent() {
        return delegate.getParent();
    }

    @Override
    public void setParent(CompositeTag tag) {
        delegate.setParent(tag);
    }
}
