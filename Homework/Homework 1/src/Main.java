import io.Input;
import io.TextInput;
import line.LineStorage;
import process.GenericIndexer;
import process.Indexer;

public class Main {
    public static void main(String[] args) {
        String filename =   "C:\\Users\\drago\\" +
                            "IdeaProjects\\Drexel-University-SE-311-Software-Architecture-II\\" +
                            "Homework\\Homework 1\\" +
                            "test\\lines.txt";
        LineStorage lineStorage = new LineStorage();

        Input input = new TextInput(lineStorage);
        input.readLines(filename);

        Indexer indexer = new GenericIndexer(lineStorage);
    }
}
