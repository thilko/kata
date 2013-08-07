package com.thilko.algorithms;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class SortTest {

    private int[] numbersToSort;

    @Before
    public void setUp(){
        numbersToSort = new int[]{4, 5, 3, 9, 8, 2, 1, 6};
    }

    @Test
    public void sort_withQuickSort_isSorted(){
        int[] result = QuickSort.sort(numbersToSort);

        assertThat(result, is(new int[]{1,2,3,4,5,6,8,9}));
    }

    @Test
    public void sort_withMergeSort_isSorted(){
        int[] result = MergeSort.sort(numbersToSort);

        assertThat(result, is(new int[]{1,2,3,4,5,6,8,9}));
    }

    @Test
    public void sort_withSelectSort_isSorted(){
        int[] result = SelectSort.sort(numbersToSort);

        assertThat(result, is(new int[]{1,2,3,4,5,6,8,9}));
    }


    @Test
    public void getMaximum_withHeapSort_isMaximum(){
        MaxHeap heap = new MaxHeap(numbersToSort);

        assertThat(heap.getMax(), is(9));
    }
}
