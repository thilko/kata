package com.thilko.algorithms;

import java.util.*;

public class MergeSort {

    public static int[] sort(int[] numbersToSort){
        if(numbersToSort.length <= 1){
            return numbersToSort;
        }

        int[] left = Arrays.copyOfRange(numbersToSort, 0, (numbersToSort.length / 2));
        int[] right = Arrays.copyOfRange(numbersToSort, (numbersToSort.length / 2), numbersToSort.length);

        int[] sortedLeft = sort(left);
        int[] sortedRight = sort(right);

        Deque<Integer> leftStack = new ArrayDeque<Integer>();
        leftStack.addAll(convert(sortedLeft));

        Deque<Integer> rightStack = new ArrayDeque<Integer>();
        rightStack.addAll(convert(sortedRight));

        List<Integer> result = new ArrayList<Integer>(sortedLeft.length + sortedRight.length);
        while(!leftStack.isEmpty() || !rightStack.isEmpty()){
            if(leftStack.isEmpty()){
                result.addAll(rightStack);
                rightStack.clear();
                continue;
            }

            if(rightStack.isEmpty()){
                result.addAll(leftStack);
                leftStack.clear();
                continue;
            }
            
            if(leftStack.getFirst() <= rightStack.getFirst()){
                result.add(leftStack.pop());
            }else{
                result.add(rightStack.pop());                
            }
            
        }

        int[] arrayResult = new int[result.size()];
        for(int i=0; i<arrayResult.length;i++){
            arrayResult[i] = result.get(i);
        }
        return arrayResult;
    }

    private static Collection<Integer> convert(int[] arrayOfInts) {
        Deque<Integer> result = new ArrayDeque<Integer>();
        for(int i: arrayOfInts){
            result.addLast(i);
        }
        
        return result;
    }
}
