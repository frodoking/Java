package cn.com.frodo.refactor.model.generalization.step_5_adapter;

public class XMLBuilder extends AbstractBuilder {
    @Override
    public XMLNode createNode(String name) {
        return new TagNode(name);
    }
}
