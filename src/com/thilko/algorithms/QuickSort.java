package com.thilko.algorithms;

import static java.lang.String.format;

public class QuickSort {


    public static int[] sort(int[] args) {
        int[] arrayToSort = args;

        quicksort(arrayToSort, 0, arrayToSort.length - 1);

        return arrayToSort;
    }

    private static int[] quicksort(int[] arrayToSort, int leftIndex, int rightIndex) {
        if (leftIndex >= rightIndex) {
            return arrayToSort;
        }

        int pivotIndex = partition(arrayToSort, leftIndex, rightIndex);

        quicksort(arrayToSort, 0, pivotIndex - 1);
        quicksort(arrayToSort, pivotIndex + 1, rightIndex);

        return new int[0];
    }

    private static int partition(int[] arrayToSort, int leftIndex, int rightIndex) {
        int right = rightIndex - 1;
        int left = leftIndex;
        int temp;

        while (left <= right) {
            System.out.println(format("compare %s %s ", arrayToSort[left], arrayToSort[rightIndex]));
            if (arrayToSort[left] > arrayToSort[rightIndex]) {
                temp = arrayToSort[left];
                arrayToSort[left] = arrayToSort[right];
                arrayToSort[right] = temp;
                right--;
            } else {
                left++;
            }
        }


        temp = arrayToSort[rightIndex];
        arrayToSort[rightIndex] = arrayToSort[left];
        arrayToSort[left] = temp;

        return left;
    }
}
