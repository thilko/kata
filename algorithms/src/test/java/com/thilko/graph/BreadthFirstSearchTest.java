package com.thilko.graph;

import com.thilko.structures.graph.Edge;
import com.thilko.structures.graph.Node;
import org.hamcrest.core.Is;
import static org.hamcrest.core.Is.is;
import org.junit.Assert;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class BreadthFirstSearchTest {

    @Test
    public void findElementByBreadthFirstSearch() {
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");

        a.connectWith(c, 2);
        a.connectWith(b, 7);
        b.connectWith(d, 8);
        c.connectWith(e, 1);

        LinkedList<Node> unvisitedNodes = new LinkedList<>();
        unvisitedNodes.add(a);

        while (!unvisitedNodes.isEmpty()) {
            Node currentElement = unvisitedNodes.poll();
            System.out.println("visited " + currentElement.getName());
            currentElement.setVisited(true);
            List<Node> childs = currentElement.getEdges().stream().map(Edge::getTo).collect(Collectors.toList());
            for (Node child : childs) {
                unvisitedNodes.addLast(child);
            }
        }

        assertThat(c.isVisited(), is(true));
    }
}
