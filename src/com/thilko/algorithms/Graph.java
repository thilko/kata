package com.thilko.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graph {

    static Random random = new Random();

    public static Node create(){
        Node root = new Node();
        addChilds(root, 5);

        return root;
    }

    private static void addChilds(Node root, int maxDepth) {
        if(maxDepth < 1) {
            return;
        }

        for(int breadth=0;breadth<5;breadth++){
            Node child = new Node();
            child.setValue(random.nextInt(50));
            root.addChild(child);

            addChilds(child, maxDepth-1);
        }
    }


    public static void main(String[] args) {
        Node n = Graph.create();
        depthFirst(n, 40, new ArrayList<Node>());
    }

    private static List<Node> depthFirst(Node n, int searchValue, List<Node> result) {
        for (Node child : n.getChilds()){
            if(child.getValue() > searchValue){
                result.add(child);
            }

            depthFirst(child, searchValue, result);
        }

        return result;
    }
}
