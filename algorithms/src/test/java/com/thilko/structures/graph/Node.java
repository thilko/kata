package com.thilko.structures.graph;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node>{
    private Integer distance = Integer.MAX_VALUE;
    private String name;
    private List<Edge> edges = new ArrayList<>();
    private Node from;
    private boolean visited;

    public Node(String name) {
        this.name = name;
    }

    public Integer getDistance() {
        return distance;
    }

    public void connectWith(Node nodeToConnect, int distance) {
        edges.add(new Edge(this, nodeToConnect, distance));
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void updateDistance(Integer newDistance) {
        distance = newDistance;
    }

    @Override
    public int compareTo(Node o) {
        return distance.compareTo(o.getDistance());
    }

    public void setFrom(Node from) {
        this.from = from;
    }

    public Node getFrom() {
        return from;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public String getName() {
        return name;
    }
}
