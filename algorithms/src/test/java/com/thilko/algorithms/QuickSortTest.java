package com.thilko.algorithms;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

public class QuickSortTest {

    private List<Integer> listToSort;

    @Before
    public void setup() {
        listToSort = range(1, 10).boxed().collect(toList());
        Collections.shuffle(listToSort);
    }

    @Test
    public void listIsSorted() {
        assertThat(quicksort(listToSort), is(asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }

    private List<Integer> quicksort(List<Integer> listToSort) {
        sort(0, listToSort.size() - 1);

        return listToSort;
    }

    private void sort(int left, int right) {
        if (left < right) {
            int pivot = quicksort(left, right);

            sort(left, pivot);
            sort(pivot+1, right);
        }
    }

    private int quicksort(int left, int right) {
        int pivot = (left + right) / 2;
        while (left < right) {
            while (listToSort.get(left) < listToSort.get(pivot) && left < pivot) {
                left++;
            }

            while (listToSort.get(right) > listToSort.get(pivot) && right > pivot) {
                right--;
            }

            if (left < right) {
                int leftValue = listToSort.get(left);
                listToSort.set(left, listToSort.get(right));
                listToSort.set(right, leftValue);
            }
        }
        return left;
    }
}
