package com.thilko.algorithms;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import java.util.Arrays;

public class QuickSort3Test {

    @Test
    public void elementsAreSortedAroundThePivot() {
        assertThat(sort(new int[]{7, 6, 2}), is(new int[]{2, 6, 7}));
    }

    @Test
    public void elementsAreSorted() {
        //assertThat(sort(new int[]{7, 6, 2, 10, 8}), is(new int[]{2, 6, 7, 8, 10}));
        assertThat(sort(new int[]{17, 7, 6, 2, 10, 8, 18}), is(new int[]{2, 6, 7, 8, 10, 17, 18}));
    }

    private int[] sort(int[] unsorted) {
        int left = 0;
        int right = unsorted.length - 1;

        sort(unsorted, left, right);

        return unsorted;
    }

    private int[] sort(int[] unsorted, int left, int right) {
        if (left < right) {
            int pivot = quicksort(unsorted, left, right);

            System.out.println("pivot: " + pivot);
            sort(unsorted, left, pivot - 1);
            sort(unsorted, pivot + 1, right);
        }

        return unsorted;
    }

    private int quicksort(int[] unsorted, int left, int right) {
        System.out.println(Arrays.toString(unsorted));
        System.out.println("l" + left + " r" + right);
        if (left < right) {
            int pivotIndex = (left + right) / 2;
            int pivot = unsorted[pivotIndex];
            System.out.println("pivot " + pivot);

            while (left < right) {
                while (unsorted[left] < pivot && left < right) {
                    left++;
                }

                while (unsorted[right] > pivot && right > left) {
                    right--;
                }

                System.out.println("left " + left + " right " + right);
                if (left < right) {
                    int temp = unsorted[left];
                    unsorted[left] = unsorted[right];
                    unsorted[right] = temp;
                }
            }
        }

        return left;
    }
}
