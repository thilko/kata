package com.thilko.algorithms;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private List<Node> connections = new ArrayList<>();
    private boolean marked;
    private int value;

    public Node(int value) {
        this.value = value;
    }


    public void addChild(Node node) {
        connections.add(node);
    }

    public List<Node> getChilds() {
        return connections;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    public void mark() {
        this.marked = true;
    }

    public boolean isNotMarked() {
        return !marked;
    }
}
