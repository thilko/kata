package com.thilko.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Graph {

    private Random random = new Random();

    List<Node> nodes = new ArrayList<>();

    private Graph(int numberOfNodes, int numberOfEdges) {
        for (int i = 0; i < numberOfNodes; i++) {
            nodes.add(new Node(i));
        }

        for (int i = 0; i < numberOfEdges; i++) {
            Node firstNode = nodes.get(random.nextInt(numberOfNodes));
            Node secondNode = nodes.get(random.nextInt(numberOfNodes));

            firstNode.addChild(secondNode);
            secondNode.addChild(firstNode);
        }
    }

    public static Graph create(int numberOfVertex, int numberOfEdges) {
        return new Graph(numberOfVertex, numberOfEdges);
    }

    public Node findWithDepthFirstFrom(int rootNodeIndex, int valueToFind) {
        Node rootNode = nodes.get(rootNodeIndex);
        return depthFirst(rootNode, valueToFind);
    }

    private Node depthFirst(Node node, int valueToFind) {
        node.mark();
        System.out.println(node);

        if (node.getValue() == valueToFind) {
            return node;
        }

        List<Node> child = node.getChilds();
        for (Node childNode : child) {
            if (childNode.isNotMarked()) {
                depthFirst(childNode, valueToFind);
            }
        }
        return node;
    }
}
