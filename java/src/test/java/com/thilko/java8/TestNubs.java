package com.thilko.java8;

import java.util.Collection;
import java.util.List;

public class TestNubs<T> {

    public void nubs(Collection<?> b) {
        throw new NegativeArraySizeException();
    }

    public int nubs(List<Integer> b) {
        for (Integer integer : b) {
            System.out.println(integer);
        }

        return 0;
    }
}
