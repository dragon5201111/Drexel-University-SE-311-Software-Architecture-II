package io;

import line.LineStorage;

public abstract class Input {
    protected LineStorage lineStorage;

    public Input(LineStorage lineStorage) {
        this.lineStorage = lineStorage;
    }

    public abstract void readLines(String filename);
}
