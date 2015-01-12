package cn.com.frodo.refactor.model.create.step_5_builder_package_composite;

import java.util.ArrayList;
import java.util.List;

public class TagNode {
	private TagNode parent;

	String attributes;
	String tagName;
	List<TagNode> children = new ArrayList<TagNode>();

	public TagNode(String name) {
		this.tagName = name;
	}

	public void add(TagNode childNode) {
		childNode.setParent(this);
		children.add(childNode);
	}

	public void setParent(TagNode parent) {
		this.parent = parent;
	}

	public TagNode getParent() {
		return this.parent;
	}

	public String getTagName() {
		return tagName;
	}
	
	public void addAttribute() {
	}

	public void addValue() {
	}

	@Override
	public String toString() {
		if (children.size() == 0) {
			return "<" + this.tagName + "/>";
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append("<" + this.tagName + ">");
			for (TagNode child : children) {
				sb.append(child.toString());
			}
			sb.append("</" + this.tagName + ">");

			return sb.toString();
		}
	}
}
