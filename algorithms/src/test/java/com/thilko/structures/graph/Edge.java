package com.thilko.structures.graph;

public class Edge {

    private Integer distance;
    private Node from;
    private Node to;

    public Edge(Node from, Node to, int distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public Integer getDistance() {
        return distance;
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }
}
