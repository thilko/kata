package com.thilko.java8;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class LamdaTest {

    @Test
    public void map_withMethodReference() {
        System.out.println(Arrays.toString(Arrays.asList("test1", "test2").stream().map(String::toUpperCase).toArray()));
    }

    @Test
    public void reduce_withMap(){
        OptionalInt reduce = Arrays.asList("4", "3", "8").stream()
                                .mapToInt(Integer::valueOf).reduce((sum, x) -> sum = sum + x);
        System.out.println(reduce.getAsInt());
    }

    @Test
    public void collector() throws IOException {
        Long numberOfFiles = Files.list(Paths.get(".")).collect(Collectors.counting());
        System.out.println(numberOfFiles);
    }
}
