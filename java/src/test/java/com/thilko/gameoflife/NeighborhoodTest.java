package com.thilko.gameoflife;

import static com.thilko.gameoflife.Cell.livingAt;
import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import java.util.List;

public class NeighborhoodTest {

    @Test
    public void living_cells_above_the_current_are_counted_correctly() {
        List<Cell> universum = asList(livingAt(0, 0), livingAt(0, 1), livingAt(0, 2));
        assertThat(countLivingNeighbors(universum, livingAt(1, 1)), is(3));

        universum = asList(livingAt(0, 0), livingAt(0, 1), livingAt(0, 2));
        assertThat(countLivingNeighbors(universum, livingAt(1, 2)), is(2));

        universum = asList(livingAt(0, 0), livingAt(0, 1), livingAt(0, 2));
        assertThat(countLivingNeighbors(universum, livingAt(1, 3)), is(1));

        universum = asList(livingAt(0, 0), livingAt(0, 1), livingAt(0, 2));
        assertThat(countLivingNeighbors(universum, livingAt(1, 4)), is(0));
    }

    @Test
    public void living_cells_below_the_current_are_counted_correctly() {
        List<Cell> universum = asList(livingAt(2, 0), livingAt(2, 1), livingAt(2, 2));
        assertThat(countLivingNeighbors(universum, livingAt(1, 1)), is(3));

        universum = asList(livingAt(2, 0), livingAt(2, 1), livingAt(2, 2));
        assertThat(countLivingNeighbors(universum, livingAt(1, 2)), is(2));

        universum = asList(livingAt(2, 0), livingAt(2, 1), livingAt(2, 2));
        assertThat(countLivingNeighbors(universum, livingAt(1, 3)), is(1));

        universum = asList(livingAt(2, 0), livingAt(2, 1), livingAt(2, 2));
        assertThat(countLivingNeighbors(universum, livingAt(1, 4)), is(0));
    }

    @Test
    public void living_cells_at_the_current_row_are_counted() {
        List<Cell> universum = asList(livingAt(1, 0), livingAt(1, 2));
        assertThat(countLivingNeighbors(universum, livingAt(1, 1)), is(2));

        universum = asList(livingAt(1, 0), livingAt(1, 2), livingAt(0, 1));
        assertThat(countLivingNeighbors(universum, livingAt(1, 1)), is(3));
    }

    @Test
    public void borders_are_dead_cells() {
        List<Cell> universum = asList(livingAt(1, 0), livingAt(1, 1));
        assertThat(countLivingNeighbors(universum, livingAt(0, 0)), is(2));

        universum = asList(livingAt(1, 0), livingAt(1, 2), livingAt(0, 1));
        assertThat(countLivingNeighbors(universum, livingAt(1, 1)), is(3));
    }

    @Test
    public void dead_cells_are_not_counted() {
        List<Cell> universum = asList(livingAt(1, 0), Cell.deadAt(1, 2));
        assertThat(countLivingNeighbors(universum, livingAt(1, 1)), is(1));
    }

    private int countLivingNeighbors(List<Cell> universum, Cell cell) {
        return new Neighborhood(universum).livingNeighborsCount(cell);
    }

}
