package cn.com.frodo.refactor.model.generalization.step_2_extra_composite;

import java.util.Enumeration;
import java.util.Vector;

/**
 * 将子类中的重复代码尽可能都提取到超类中
 * @author XuWei4
 *
 */
public abstract class CompositeTag extends Tag {

	private Vector<Node> children;

	public CompositeTag(int tagBegin, int tagEnd, String tagContents, String tagLine) {
		super(tagBegin, tagEnd, tagContents, tagLine);
	}

	public String toPlainTextString() {
		StringBuffer sb = new StringBuffer();
		Node node;
		for (Enumeration<Node> e = children(); e.hasMoreElements();) {
			node = e.nextElement();
			sb.append(node.toPlainTextString());
		}

		return sb.toString();
	}

	public Enumeration<Node> children() {
		return children.elements();
	}
}
