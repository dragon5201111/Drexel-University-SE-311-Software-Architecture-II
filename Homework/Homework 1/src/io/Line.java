package io;

public class Line{
    private String text;
    private int index;

    public Line(String text, int index) {
        this.text = text;
        this.index = index;
    }

    public String getText() {
        return this.text;
    }

    public int getIndex() {
        return this.index;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
