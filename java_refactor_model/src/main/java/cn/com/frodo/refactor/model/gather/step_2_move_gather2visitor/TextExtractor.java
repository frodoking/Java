package cn.com.frodo.refactor.model.gather.step_2_move_gather2visitor;

import cn.com.frodo.refactor.model.simplify.step_3_move_decoration_2_decorator.Translate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 典型的观察者模式，高大上啊.一个方法从不同的类中聚集信息。 把聚集工作搬移到一个能够访问每个类以便聚集信息的Visitor中
 *
 * @author frodoking
 */
public class TextExtractor implements NodeVisitor {
    StringBuffer result;

    boolean isPreTag;
    boolean isScriptTag;

    public String extractText() {
        result = new StringBuffer();
        // ....
        List<Node> nodes = new ArrayList<Node>();
        for (Iterator<Node> i = nodes.iterator(); i.hasNext(); ) {
            i.next().accept(this);
        }
        return result.toString();
    }

    @Override
    public void visitTag(Tag tag) {
        String tagName = tag.getTagName();
        if (tagName.equalsIgnoreCase("PRE"))
            isPreTag = true;
        else if (tagName.equalsIgnoreCase("SCRIPT"))
            isScriptTag = true;
    }

    @Override
    public void visitEndTag(EndTag endTag) {
        String tagName = endTag.getTagName();
        if (tagName.equalsIgnoreCase("PRE"))
            isPreTag = false;
        else if (tagName.equalsIgnoreCase("SCRIPT"))
            isScriptTag = false;
    }

    @Override
    public void visitLinkTag(LinkTag link) {
        if (isPreTag)
            result.append(link.getLinkText());
        else
            collapse(result, Translate.decode(link.getLinkText()));

        if (link.getLinks()) {
            result.append("<");
            result.append(link.getLink());
            result.append(">");
        }
    }

    private void collapse(StringBuffer result, String decode) {
        result.append(decode);
    }

    @Override
    public void visitStringNode(StringNode stringNode) {
        // .............
    }

}
