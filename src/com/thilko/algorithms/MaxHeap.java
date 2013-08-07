package com.thilko.algorithms;

public class MaxHeap {

    private final int[] arrayStore;
    private Integer max;
    private int size;

    public MaxHeap(int[] arrayToSort){
        this.arrayStore = new int[arrayToSort.length + 1];
        this.size = arrayStore.length;

        System.arraycopy(arrayToSort, 0, arrayStore, 1, arrayToSort.length);
        for(int i=size/2; i>=1;i--){
            sink(i);
        }

        for(int item: arrayStore){
            System.out.println(item);
        }

    }

    private void sink(int indexToSink) {
        while(indexToSink * 2 < arrayStore.length) {
            int nextIndex = indexToSink  *2;
            if (nextIndex + 1 < size && lesser(nextIndex, nextIndex + 1)) {
                nextIndex++;
            }

            if(!lesser(indexToSink, nextIndex)){
                break;
            }

            int temp = arrayStore[indexToSink];
            arrayStore[indexToSink] = arrayStore[nextIndex];
            arrayStore[nextIndex] = temp;

            indexToSink = nextIndex;
        }
    }

    private boolean lesser(int indexToSink, int indexToCompare) {
        return arrayStore[indexToSink] < arrayStore[indexToCompare];
    }

    public int getMax() {
        return arrayStore[1];
    }
}
