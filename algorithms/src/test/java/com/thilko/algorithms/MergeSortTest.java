package com.thilko.algorithms;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static org.hamcrest.core.Is.is;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MergeSortTest {

    @Test
    public void listIsSorted() {
        List<Integer> listToSort = range(1, 10).boxed().collect(toList());
        Collections.shuffle(listToSort);

        Assert.assertThat(sort(listToSort), is(asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }

    private List<Integer> sort(List<Integer> listToSort) {
        if (listToSort.size() == 1) {
            return listToSort;
        }

        List<Integer> leftSublist = sort(listToSort.subList(0, listToSort.size() / 2));
        List<Integer> rightSublist = sort(listToSort.subList(listToSort.size() / 2, listToSort.size()));

        return merge(leftSublist, rightSublist);
    }

    private List<Integer> merge(List<Integer> leftSublist, List<Integer> rightSublist) {
        Integer leftIndex = 0;
        Integer rightIndex = 0;
        List<Integer> sortedList = new ArrayList<>();

        while ((leftIndex < leftSublist.size() && rightIndex < rightSublist.size())) {
            if (leftSublist.get(leftIndex) < rightSublist.get(rightIndex)) {
                sortedList.add(leftSublist.get(leftIndex));
                leftIndex++;
            } else {
                sortedList.add(rightSublist.get(rightIndex));
                rightIndex++;
            }
        }

        while (leftIndex < leftSublist.size()) {
            sortedList.add(leftSublist.get(leftIndex));
            leftIndex++;
        }

        while (rightIndex < rightSublist.size()) {
            sortedList.add(rightSublist.get(rightIndex));
            rightIndex++;
        }

        return sortedList;
    }
}
