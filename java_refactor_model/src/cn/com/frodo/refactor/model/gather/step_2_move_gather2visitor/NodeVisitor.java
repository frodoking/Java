package cn.com.frodo.refactor.model.gather.step_2_move_gather2visitor;

public interface NodeVisitor {
	void visitTag(Tag tag);

	void visitEndTag(EndTag endTag);

	void visitLinkTag(LinkTag link);

	void visitStringNode(StringNode stringNode);
}
