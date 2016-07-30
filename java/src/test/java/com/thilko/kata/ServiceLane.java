package com.thilko.kata;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServiceLane {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String nAndT = input.nextLine();
        String[] width = input.nextLine().split(" ");

        Integer numberOfTests = Integer.valueOf(nAndT.split(" ")[1]);
        List<String> result = new ArrayList<>();
        for (int i = 0; i < numberOfTests; i++) {
            String[] currentTestCase = input.nextLine().split(" ");
            Integer enterIndex = Integer.valueOf(currentTestCase[0]);
            Integer exitIndex = Integer.valueOf(currentTestCase[1]);

            Integer largestVehicle = 3;
            for (int j = enterIndex; j <= exitIndex; j++) {
                largestVehicle = Math.min(largestVehicle, Integer.valueOf(width[j]));
            }
            result.add(largestVehicle.toString());
        }

        for(int i=0;i<result.size();i++){
            System.out.println(result.get(i));
        }
    }
}
