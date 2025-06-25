package io;
import java.util.Arrays;
import java.util.List;

public class TextInput extends Input {

    @Override
    List<String> parseLineText(String text) {
        return Arrays.stream(text
                        .trim()
                        .replaceAll("\\s+", " ")
                        .split(" "))
                        .toList();
    }
}
