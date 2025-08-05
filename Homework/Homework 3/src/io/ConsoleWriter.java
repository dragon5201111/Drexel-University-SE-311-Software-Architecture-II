package io;

import java.util.List;

public class ConsoleWriter implements Writer{
    @Override
    public void writeLines(List<String> lines) {
        for (String line : lines){
            System.out.println(line);
        }
    }
}
