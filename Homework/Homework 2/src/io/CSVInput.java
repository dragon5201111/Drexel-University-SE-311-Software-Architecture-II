package io;

import line.LineStorage;

import java.util.Arrays;
import java.util.List;

public class CSVInput extends Input{
    @Override
    void setWordDelimiter(LineStorage lineStorage) {
        lineStorage.setWordDelimiter(".");
    }

    @Override
    List<String> parseWords(String text) {
        return Arrays.stream(text
                        .trim()
                        .replaceAll("\\s+", " ")
                        .split("\\."))
                .toList();
    }
}
