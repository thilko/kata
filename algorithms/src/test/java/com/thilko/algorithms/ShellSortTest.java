package com.thilko.algorithms;

import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.range;
import static org.hamcrest.core.Is.is;
import org.junit.Assert;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ShellSortTest {

    private List<Integer> listToSort;

    private List<Integer> steps = Arrays.asList(15, 7, 3, 1);

    @Before
    public void setup() {
        listToSort = range(1, 10).boxed().collect(toList());
        Collections.shuffle(listToSort);
    }

    @Test
    public void listIsSorted() {
        assertThat(sort(listToSort), is(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
    }

    private List<Integer> sort(List<Integer> listToSort) {
        for (int step : steps) {
            for (int i = step; i < listToSort.size(); i++) {
                int index = i;

                Integer elementToSort = listToSort.get(index);

                while (index >= step && elementToSort <= listToSort.get(index - step)) {
                    listToSort.set(index, listToSort.get(index - step));
                    index -= step;
                }

                listToSort.set(index, elementToSort);
            }
        }

        return listToSort;
    }
}
