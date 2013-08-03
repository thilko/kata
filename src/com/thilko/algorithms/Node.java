package com.thilko.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Node {
    private List<Node> connections = new ArrayList<Node>();
    private boolean marked;
    private int value;


    public void addChild(Node node) {
        connections.add(node);
    }

    public List<Node> getChilds() {
        return connections;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
