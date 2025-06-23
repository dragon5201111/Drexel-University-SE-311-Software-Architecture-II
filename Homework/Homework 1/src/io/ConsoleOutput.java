package io;

import java.util.List;

public class ConsoleOutput implements Output{
    @Override
    public void writeLines(List<String> lines) {
        for (String line : lines) {
            System.out.println(line);
        }
    }
}
