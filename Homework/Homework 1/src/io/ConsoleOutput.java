package io;

import java.util.List;

public class ConsoleOutput implements Output{
    @Override
    public void writeLines(List<Line> lines) {
        for (Line line : lines) {
            System.out.println(line);
        }
    }
}
