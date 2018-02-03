package application;

import pojo.Node;

import java.util.ArrayList;
import java.util.HashMap;


public class Tree<I, A> {
    private static final HashMap<Long, Node> map = new HashMap<>();
    private static Node root;

    public Tree(Node root) {
        this.root = root;
        map.put(root.getId(), root);
    }

    public void addChild(Node child)  {
        Node parent = map.get(child.getParentId());
        parent.getChildren().add(child);
        map.put(child.getId(), child);
    }

    public static void updateNode(Node node)
    {
        map.put(node.getId(),node);
    }

    public static Node getTree() {

        Node node = map.get(Tree.root.getId());
        return node;
    }

    public static ArrayList<Node> getTree(Long parentId) {

        Node node = map.get(parentId);

        return node.getChildren();
    }

}