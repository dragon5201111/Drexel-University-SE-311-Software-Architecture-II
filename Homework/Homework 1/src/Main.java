import io.Input;
import io.Line;
import io.TxtInput;
import processing.AlphabeticalSorter;
import processing.CircularShifter;
import processing.Sorter;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String inputFile = "C:\\Users\\drago\\OneDrive\\Desktop\\School\\" +
                "Summer 2024 - 2025\\SE 311\\Drexel-University-SE-311-Software-Architecture-II\\" +
                "Homework\\Homework 1\\test\\lines.txt";

        Input input = new TxtInput();
        List<Line> lines = input.readLines(inputFile);

        Sorter sorter = new AlphabeticalSorter();
        CircularShifter shifter = new CircularShifter();

        List<Line> modifiedLines = sorter.sortLines(shifter.generateShiftedLines(lines));
        for (Line line : modifiedLines) {
            System.out.println(line + " " + line.getIndex());
        }
    }
}
