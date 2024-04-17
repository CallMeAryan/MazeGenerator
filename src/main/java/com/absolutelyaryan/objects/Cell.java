package com.absolutelyaryan.objects;

public class Cell {
    private Cell node;

    private final int x;

    private final int y;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getx() {
        return this.x;
    }

    public int gety() {
        return this.y;
    }

    public Cell getNext() {
        return this.node;
    }

    public void setNext(Cell node) {
        this.node = node;
    }

    public String toString() {
        return "[" + this.x + ":" + this.y + "]";
    }
}