import io.ConsoleOutput;
import io.Input;
import io.Output;
import io.TextInput;
import line.LineStorage;
import process.AlphabeticalSorter;
import process.Shifter;
import process.Sorter;

public class MasterControl {
    public static void main(String[] args) {
        String filename =   "C:\\Users\\drago\\" +
                            "IdeaProjects\\Drexel-University-SE-311-Software-Architecture-II\\" +
                            "Homework\\Homework 1\\" +
                            "test\\lines.txt";
        LineStorage lineStorage = new LineStorage();
        Input input = new TextInput();
        Output output = new ConsoleOutput();
        input.readLines(filename, lineStorage);

        Shifter shifter = new Shifter(lineStorage);
        Sorter sorter = new AlphabeticalSorter(shifter);


    }
}
