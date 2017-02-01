package com.thilko.structures;

import java.util.List;

public class Heap {

    public static List<Integer> buildHeap(List<Integer> unsorted) {
        System.out.println(unsorted);
        for (int i = (unsorted.size() / 2); i >= 0; i--) {
            heapify(unsorted, i);
        }

        return unsorted;
    }

    public static void heapify(List<Integer> unsorted, int currentIndex) {
        int largestIndex = currentIndex;
        if (left(unsorted, currentIndex) && unsorted.get(currentIndex * 2) > unsorted.get(largestIndex)) {
            largestIndex = currentIndex * 2;
        }

        if (right(unsorted, currentIndex) && unsorted.get(currentIndex * 2 + 1) > unsorted.get(largestIndex)) {
            largestIndex = currentIndex * 2 + 1;
        }

        if (largestIndex != currentIndex) {
            int temp = unsorted.get(currentIndex);
            unsorted.set(currentIndex, unsorted.get(largestIndex));
            unsorted.set(largestIndex, temp);
            heapify(unsorted, largestIndex);
        }
    }

    private static boolean right(List<Integer> unsorted, int currentIndex) {
        return currentIndex * 2 + 1 < unsorted.size();
    }

    private static boolean left(List<Integer> unsorted, int currentIndex) {
        return currentIndex * 2 < unsorted.size();
    }
}
