package cn.com.frodo.refactor.model.generalization.step_5_adapter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ElementAdapter implements XMLNode {
	Element element;
	Document document;

	public ElementAdapter(Element element, Document document) {
		this.element = element;
		this.document = document;
	}

	public Element getElement() {
		return this.element;
	}

	public void addAttribute(String name, String value) {
		getElement().setAttribute(name, value);
	}

	public void add(ElementAdapter currentNode) {
		getElement().appendChild(currentNode.getElement());
	}

	@Override
	public void add(XMLNode childNode) {
		ElementAdapter childElement = (ElementAdapter) childNode;
		getElement().appendChild(childElement.getElement());
	}

	@Override
	public void addValue(String value) {
		getElement().appendChild(document.createTextNode(value));
	}

}
