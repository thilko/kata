package com.thilko.gameoflife;

public class StandardRules {
    public boolean aliveInNextGeneration(Cell cell, int livingNeighbors) {
        if (cell.isAlive() && livingNeighbors > 3) {
            return false;
        } else if (!cell.isAlive() && livingNeighbors > 3) {
            return false;
        }

        return livingNeighbors > 2;
    }
}
