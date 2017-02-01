package com.thilko.algorithms;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class InsertSortTest {

    @Test
    public void listIsSorted() {
        List<Integer> sortedList = sort(Arrays.asList(5, 4, 6, 3, 2, 10));

        assertThat(sortedList, is(Arrays.asList(2, 3, 4, 5, 6, 10)));
    }

    private List<Integer> sort(List<Integer> unsorted) {
        for (int rightIndex = 1; rightIndex < unsorted.size(); rightIndex++) {
            int leftIndex = rightIndex;
            Integer itemToSort = unsorted.get(leftIndex);

            while (leftIndex > 0 && itemToSort < unsorted.get(leftIndex - 1)) {
                unsorted.set(leftIndex, unsorted.get(leftIndex - 1));
                leftIndex--;
            }

            unsorted.set(leftIndex, itemToSort);
        }
        return unsorted;
    }
}
