package com.thilko.algorithms;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class SortTest {

    @Test
    public void sort_withQuickSort_isSorted(){
        int[] args = {4, 5, 3, 9, 8, 2, 1, 6};
        int[] result = QuickSort.sort(args);

        assertThat(result, is(new int[]{1,2,3,4,5,6,8,9}));
    }
}
