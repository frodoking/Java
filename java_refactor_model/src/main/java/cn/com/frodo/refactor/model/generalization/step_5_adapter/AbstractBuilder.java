package cn.com.frodo.refactor.model.generalization.step_5_adapter;

import java.util.Stack;

public abstract class AbstractBuilder {
    private XMLNode rootNode;
    private XMLNode parentNode;
    private XMLNode currentNode;

    private Stack<XMLNode> history = new Stack<XMLNode>();

    public void addAttribute(String name, String value) {
        currentNode.addAttribute(name, value);
    }

    public void addChild(String childTagName) {
        XMLNode childNode = createNode(childTagName);

        currentNode.add(childNode);
        parentNode = currentNode;
        currentNode = childNode;
        history.push(currentNode);
    }

    public void addSibling(String siblingTagName) {
        if (currentNode == rootNode)
            throw new RuntimeException();

        XMLNode siblingNode = createNode(siblingTagName);
        currentNode.add(siblingNode);

        currentNode = siblingNode;
        history.pop();
        history.push(currentNode);
    }

    public void addValue(String value) {
        currentNode.addValue(value);
    }

    public abstract XMLNode createNode(String name);
}
