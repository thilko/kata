package com.thilko.kata;

import java.util.ArrayList;
import java.util.Scanner;

public class UtopianTree {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Integer numberOfTestCases = Integer.valueOf(input.next());

        ArrayList<Integer> numberOfCycles = new ArrayList<>();
        for (int i = 0; i < numberOfTestCases; i++) {
            numberOfCycles.add(Integer.valueOf(input.next()));
        }

        for (Integer numberOfCycle : numberOfCycles) {
            System.out.println(treeHeight(numberOfCycle));
        }
    }

    private static int treeHeight(Integer numberOfCycles) {
        int height = 1;
        for (int i = 1; i <= numberOfCycles; i++) {
            if (i % 2 == 1) {
                height = height + height;
            } else {
                height += 1;
            }
        }
        return height;
    }
}
