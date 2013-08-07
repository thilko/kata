package com.thilko.algorithms;

public class MaxHeap {

    private int[] arrayStore;
    private Integer max;
    private int size;

    public MaxHeap(int[] arrayToSort){
        this.arrayStore = new int[arrayToSort.length + 1];
        this.size = arrayStore.length;

        System.arraycopy(arrayToSort, 0, arrayStore, 1, arrayToSort.length);
        for(int i=size/2; i>=1;i--){
            sink(i);
        }

        printArray();

    }

    private void printArray() {
        System.out.println("----");
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

            exchange(indexToSink, nextIndex);

            indexToSink = nextIndex;
        }
    }

    private void exchange(int first, int second) {
        int temp = arrayStore[first];
        arrayStore[first] = arrayStore[second];
        arrayStore[second] = temp;
    }

    private boolean lesser(int indexToSink, int indexToCompare) {
        return arrayStore[indexToSink] < arrayStore[indexToCompare];
    }

    public int getMax() {
        int maxElement = arrayStore[1];
        exchange(1, size - 1);
        int[] resizedArray = new int[size-1];
        System.arraycopy(arrayStore, 0, resizedArray, 0, size-1);
        arrayStore = resizedArray;
        size = arrayStore.length;
        sink(1);

        printArray();

        return maxElement;
    }

    public int size() {
        return arrayStore.length - 1;
    }

    public void insert(int newElement) {
        int[] resizedArray = new int[arrayStore.length];
        System.arraycopy(arrayStore, 0, resizedArray, 0, arrayStore.length);
        resizedArray[resizedArray.length-1] = newElement;
        arrayStore = resizedArray;
        size = arrayStore.length;

        swim(size-1);
        printArray();
    }

    private void swim(int elementToSwim) {
        while(elementToSwim > 1 && lesser(elementToSwim /2, elementToSwim)){
            int temp = arrayStore[elementToSwim /2];
            arrayStore[elementToSwim / 2] = arrayStore[elementToSwim];
            arrayStore[elementToSwim] = temp;

            elementToSwim = elementToSwim /2;
        }
    }
}
