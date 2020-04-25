package cn.com.frodo.refactor.model.generalization.step_2_extra_composite;

import java.util.Enumeration;
import java.util.Map;

public class LinkTag extends CompositeTag {

    private Map<String, String> parsed;

    public LinkTag(int tagBegin, int tagEnd, String tagContents, String tagLine) {
        super(tagBegin, tagEnd, tagContents, tagLine);
    }

    public String toHTML() {
        StringBuffer sb = new StringBuffer();
        putLinkStartTagInto(sb);
        Node node;
        for (Enumeration<Node> e = children(); e.hasMoreElements(); ) {
            node = e.nextElement();
            sb.append(node.toHTML());
        }
        sb.append("</A>");
        return sb.toString();
    }

    public void putLinkStartTagInto(StringBuffer sb) {
        sb.append("<A>");
        String key, value;
        int i = 0;
        for (Enumeration<String> e = (Enumeration<String>) parsed.keySet(); e.hasMoreElements(); ) {
            key = e.nextElement();
            i++;
            LinkTag node = null;
            sb.append(node.toHTML());
        }
    }

}
