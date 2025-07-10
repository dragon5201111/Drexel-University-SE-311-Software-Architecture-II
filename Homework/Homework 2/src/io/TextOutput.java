package io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TextOutput implements Output{
    private final String fileName;
    public TextOutput(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void writeLines(List<String> lines) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.fileName))) {
            for (String line : lines) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
