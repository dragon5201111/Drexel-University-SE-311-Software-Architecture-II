package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TextOutput implements Output{

    @Override
    public void writeLines(List<String> lines, String outputName) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputName))) {
            for (String line : lines) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
