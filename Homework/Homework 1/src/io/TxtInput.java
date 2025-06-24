package io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TxtInput implements Input {
    @Override
    public List<Line> readLines(String filename) {
        try {
            Scanner scanner = new Scanner(new File(filename));

            List<Line> lines = new ArrayList<>();
            for(int index = 1; scanner.hasNextLine(); index++) {
                String line = scanner.nextLine();
                lines.add(new Line(line, index));
            }

            return lines;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
