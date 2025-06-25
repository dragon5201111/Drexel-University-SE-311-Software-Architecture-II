package io;

import line.Line;
import line.LineStorage;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class TextInput extends Input {
    public TextInput(LineStorage lineStorage) {
        super(lineStorage);
    }

    @Override
    public void readLines(String filename) {
        List<Line> lines = new ArrayList<>();
        try{
            Scanner scanner = new Scanner(new File(filename));
            for(int i = 1; scanner.hasNextLine(); i++){
                String text = scanner.nextLine();
                List<String> words = Arrays.stream(text.trim().replaceAll("\\s+", " ").split(" ")).toList();
                Line newLine = new Line(text, i, words);
                lines.add(newLine);
            }
        }catch (Exception _){
        }
        this.lineStorage.setLines(lines);
    }
}
