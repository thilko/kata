package com.thilko.routing;

import com.thilko.structures.graph.Edge;
import com.thilko.structures.graph.Node;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import java.util.PriorityQueue;

public class DijkstraTest {

    @Test
    public void bla() {
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");
        Node f = new Node("f");
        Node g = new Node("g");


        a.updateDistance(0);

        a.connectWith(b, 2);
        b.connectWith(e, 5);
        e.connectWith(f, 2);
        f.connectWith(g, 10);
        g.connectWith(d, 1);

        a.connectWith(c, 4);
        c.connectWith(d, 12);

        PriorityQueue<Node> nodes = new PriorityQueue<>();
        nodes.add(a);

        while (!nodes.isEmpty()) {
            Node currentNode = nodes.poll();

            for (Edge edge : currentNode.getEdges()) {
                if (currentNode.getDistance() + edge.getDistance() < edge.getTo().getDistance()) {
                    edge.getTo().updateDistance(currentNode.getDistance() + edge.getDistance());
                    edge.getTo().setFrom(currentNode);
                    nodes.add(edge.getTo());
                }
            }
        }

        assertThat(d.getDistance(), is(16));
        assertThat(d.getFrom(), is(c));

    }

}
