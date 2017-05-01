package com.thilko.gameoflife;

public class Cell {
    private final int x;
    private final int y;
    private boolean alive;

    public Cell(int x, int y, boolean alive) {
        this.x = x;
        this.y = y;
        this.alive = alive;
    }

    public static Cell livingAt(int x, int y) {
        return new Cell(x, y, true);
    }

    public static Cell deadAt(int x, int y) {
        return new Cell(x, y, false);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isDirectNeighbor(Cell cell) {
        return (Math.abs(cell.getY() - y)) <= 1 && (Math.abs(cell.getX() - x)) <= 1;
    }


    public boolean isAlive() {
        return alive;
    }
}
