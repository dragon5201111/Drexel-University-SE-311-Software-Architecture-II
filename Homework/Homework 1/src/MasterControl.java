import io.Input;
import io.TextInput;
import line.LineStorage;
import process.*;

public class MasterControl {
    public static void main(String[] args) {
        String fileName = "C:\\Users\\drago\\IdeaProjects\\Drexel-University-SE-311-Software-Architecture-II\\Homework\\Homework 1\\test\\lines.txt";
        LineStorage lineStorage = new LineStorage();

        Input input = new TextInput();
        input.readLines(fileName, lineStorage);

        KeywordSearch keywordSearch = new KeywordSearch(lineStorage);
        for (String line : keywordSearch.getKeywordLines("Tiger")){
            System.out.println(line);
        }

        System.out.println("------------------------");

        Shifter shifter = new Shifter(lineStorage);
        Sorter sorter = new AlphabeticalSorter(shifter);
        for (String sortedLine : sorter.getSortedLines()){
            System.out.println(sortedLine);
        }

        System.out.println("------------------------");

        Indexer indexer = new GenericIndexer(lineStorage);
        for(String indexedWord : indexer.getIndexedWords()){
            System.out.println(indexedWord);
        }

    }
}
