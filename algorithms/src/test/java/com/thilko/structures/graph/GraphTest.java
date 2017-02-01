package com.thilko.structures.graph;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class GraphTest {

    @Test
    public void twoNodes_canBeConnected(){
        Node a = new Node("A");
        Node b = new Node("B");

        a.connectWith(b, 5);

        assertThat(a.getEdges().size(), is(1));
        assertThat(a.getEdges().get(0).getDistance(), is(5));
        assertThat(a.getEdges().get(0).getFrom(), is(a));
        assertThat(a.getEdges().get(0).getTo(), is(b));
    }

    @Test
    public void shortedPath_canBeCaldulated(){
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");

        a.connectWith(b, 3);
        b.connectWith(c, 5);
        a.connectWith(d, 6);
        d.connectWith(c, 1);



        assertThat(d.getDistance(), is(7));
    }
}
