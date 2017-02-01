package com.thilko.algorithms;

import com.thilko.structures.Heap;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class HeapSortTest {

    @Test
    public void listIsSorted() {
        List<Integer> listToSort = range(1, 10).boxed().collect(toList());
        Collections.shuffle(listToSort);

        assertThat(sort(listToSort), is(asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }

    private List<Integer> sort(List<Integer> listToSort) {
        List<Integer> heap = Heap.buildHeap(listToSort);

        for (int i = heap.size() - 1; i >= 0; i--) {
            Collections.swap(heap, 0, i);
            Heap.heapify(heap.subList(0, i), 0);
        }

        return heap;
    }
}
