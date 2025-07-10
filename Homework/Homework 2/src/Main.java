import io.Input;
import io.Output;
import io.TextInput;
import io.TextOutput;
import line.LineStorage;
import process.AlphabeticalSorter;
import process.Shifter;
import process.Sorter;


public class Main {
    public static void main(String[] args) {
        LineStorage lineStorage = new LineStorage();
        String testFile = "C:\\Users\\drago\\OneDrive\\Desktop\\School\\Summer 2024 - 2025\\SE 311\\Drexel-University-SE-311-Software-Architecture-II\\Homework\\Homework 2\\test\\test.txt";
        Input input = new TextInput();
        input.readLines(testFile, lineStorage);

        Shifter shifter = new Shifter(lineStorage);
        Sorter  sorter = new AlphabeticalSorter(shifter);
        Output output = new TextOutput("test/testOut.txt");
        output.writeLines(sorter.getSortedLines());
    }

}
