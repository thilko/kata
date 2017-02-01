package com.thilko.structures;

import static com.thilko.structures.Heap.buildHeap;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class HeapTest {

    @Test
    public void buildMinHeap_returnsAHeapAsArray() {
        assertThat(buildHeap(asList(3, 6, 7, 1, 9, 8, 2)), is(asList(9, 8, 7, 6, 3, 2, 1)));
    }

}
