package cn.com.frodo.refactor.model.simplify.step_5_composite_replace_hidetree;

import java.util.ArrayList;
import java.util.List;

/**
 * 代表树中所有隐式叶子
 *
 * @author frodoking
 */
public class TagNode {
    private String name = "";
    private String value = "";
    private StringBuffer attributes;

    private List<TagNode> children;

    public TagNode(String name) {
        this.name = name;
        attributes = new StringBuffer();
    }

    private List<TagNode> children() {
        return children == null ? children = new ArrayList<TagNode>() : children;
    }

    public void addAttribute(String attribute, String value) {
        attributes.append(" ");
        attributes.append(attribute);
        attributes.append("='");
        attributes.append(value);
        attributes.append("'");
    }

    public void add(TagNode child) {
        children().add(child);
    }

    public void addValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        String result;
        if (children().size() == 0) {
            result = "<" + name + " " + attributes + "='" + value + "/>";
        } else {
            result = "<" + name + " " + attributes + "='" + value + ">";
            for (int i = 0; i < children().size(); i++) {
                result += children.get(i).toString();
            }
            result += "</" + name + ">";
        }

        return result;
    }

}
