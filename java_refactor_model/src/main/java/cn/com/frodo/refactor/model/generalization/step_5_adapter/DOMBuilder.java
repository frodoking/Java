package cn.com.frodo.refactor.model.generalization.step_5_adapter;

import org.w3c.dom.Document;

public class DOMBuilder extends AbstractBuilder {
    private Document document;

    public DOMBuilder(Document document) {
        this.document = document;
    }

    @Override
    public XMLNode createNode(String name) {
        return new ElementAdapter(document.createElement(name), document);
    }
}
