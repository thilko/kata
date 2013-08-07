package com.thilko.algorithms;

public class SelectSort {

    public static int[] sort(int[] arrayToSort) {
        int[] result = arrayToSort;
        for (int index = 0; index < arrayToSort.length - 1; index++) {
            int smallestIndex = 0;
            int smallest = Integer.MAX_VALUE;
            for(int searchIndex=index;searchIndex<arrayToSort.length;searchIndex++){
                if(arrayToSort[searchIndex] <= smallest){
                    smallest = arrayToSort[searchIndex];
                    smallestIndex = searchIndex;
                }
            }

            int temp = arrayToSort[smallestIndex];
            arrayToSort[smallestIndex] = arrayToSort[index];
            arrayToSort[index] = temp;
        }

        return result;
    }
}
