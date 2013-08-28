package com.thilko.algorithms;

import org.junit.Test;

public class GraphTest {

    @Test
    public void depthFirst(){
        Graph graph = Graph.create(20, 15);
        graph.findWithDepthFirstFrom(0, 56);

    }

}
