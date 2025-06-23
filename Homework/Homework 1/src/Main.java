import io.Input;
import io.TxtInput;
import processing.AlphabeticalSorter;
import processing.Sorter;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        String inputFile = "C:\\Users\\drago" +
                "\\IdeaProjects\\Drexel-University-SE-311-Software-Architecture-II" +
                "\\Homework\\Homework 1\\Test\\lines.txt";
        Input input = new TxtInput();
        Sorter sorter = new AlphabeticalSorter();

        List<String> inputLines = sorter.sortLines(input.readLines(inputFile));

    }
}
