package cn.com.frodo.refactor.model.create.step_5_builder_package_composite;

public class TagBuilder {
	private TagNode rootNode;
	private TagNode currentNode;

	public TagBuilder(String rootTagName) {
		this.rootNode = new TagNode(rootTagName);
		this.currentNode = rootNode;
	}

	public String toXML() {
		return this.rootNode.toString();
	}

	public void addChild(String childTagName) {
		addTo(currentNode, childTagName);
	}

	public void addSibling(String siblingTagName) {
		addTo(currentNode.getParent(), siblingTagName);
	}

	public void addToParent(String parentTagName, String childTagName) {
		TagNode parentNode = findParentBy(parentTagName);
		if (parentNode == null) {
			throw new RuntimeException("missing parent tag : " + parentTagName);
		}

		addTo(parentNode, childTagName);
	}

	private void addTo(TagNode parentNode, String tagName) {
		currentNode = new TagNode(tagName);
		parentNode.add(currentNode);
	}

	private TagNode findParentBy(String parentName) {
		TagNode parentNode = currentNode;
		while (parentNode != null) {
			if (parentName.equals(parentNode.getTagName()))
				return parentNode;

			parentNode = parentNode.getParent();
		}

		return null;
	}

}
