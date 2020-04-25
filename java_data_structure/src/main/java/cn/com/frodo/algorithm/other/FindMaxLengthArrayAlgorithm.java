package cn.com.frodo.algorithm.other;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by frodo on 2017/8/25.
 */
public class FindMaxLengthArrayAlgorithm {
    public String[] find(String[] array, String start, String end) {
        int startIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < array.length; i++) {
            String item = array[i];
            if (item.startsWith(start)) {
                startIndex = i;
            }
            if (item.endsWith(end)) {
                endIndex = i;
            }
        }
        String[] newArray = new String[endIndex - startIndex + 1];
        System.arraycopy(array, startIndex, newArray, 0, endIndex - startIndex + 1);

        return find(newArray);
    }

    public String[] find(String[] array) {
        Node rootNode = new Node();
        rootNode.value = array[0];

        build(rootNode, array, 1);

        rootNode.print();

        System.out.println("deep = [" + rootNode.deep() + "]");

        List<String> list = new ArrayList<String>();
        rootNode.findDeepList(list);

        return list.toArray(new String[list.size()]);
    }

    private void build(Node root, String[] array, int position) {
        for (int i = position; i < array.length; i++) {
            if (root.value.charAt(root.value.length() - 1) == array[i].charAt(0)) {
                build(root, array[i]);
            }
        }
        if (root.children != null) {
            for (int i = 0; i < root.children.size(); i++) {
                build(root.children.get(i), array, position + root.children.size());
            }
        }
    }

    private void build(Node node, String text) {
        Node child = new Node();
        child.value = text;
        if (node.children == null) {
            node.children = new ArrayList<>();
        }
        node.children.add(child);
    }

    class Node {
        String value;
        List<Node> children;

        void print() {
            System.out.println(value);
            if (children != null) {
                for (Node child : children) {
                    child.print();
                }
            }
        }

        int deep() {
            int deep = 0;
            if (children != null) {
                for (Node child : children) {
                    if (child.deep() > deep) {
                        deep = child.deep();
                    }
                }
            }
            return deep + 1;
        }

        void findDeepList(List<String> list) {
            list.add(value);
            int deep = 0;
            if (children != null) {
                Node nextNode = null;
                for (Node child : children) {
                    if (child.deep() > deep) {
                        deep = child.deep();
                        nextNode = child;
                    }
                }
                nextNode.findDeepList(list);
            }
        }
    }
}
