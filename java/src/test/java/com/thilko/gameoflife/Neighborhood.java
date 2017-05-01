package com.thilko.gameoflife;

import java.util.List;

public class Neighborhood {

    private List<Cell> universum;

    public Neighborhood(List<Cell> universum) {
        this.universum = universum;
    }

    public int livingNeighborsCount(Cell currentCell) {
        int livingNeighbors = 0;
        for (Cell cell : universum) {
            if (currentCell.isDirectNeighbor(cell) && cell.isAlive()) {
                livingNeighbors++;
            }
        }
        return livingNeighbors;
    }
}
