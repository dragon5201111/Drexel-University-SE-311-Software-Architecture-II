import factory.IndexerFactory;
import factory.SorterFactory;
import io.Input;
import io.Output;
import line.LineStorage;
import process.*;
import resources.OptionReader;


public class Main {
    public static void main(String[] args) {
        OptionReader.readOptions();


        String inputFileName = OptionReader.getString("InputFileName");
        Input input = (Input) OptionReader.getObjectFromStr(OptionReader.getString("Input"));

        LineStorage lineStorage = new LineStorage();
        input.readLines(inputFileName, lineStorage);


        Shifter shifter = new Shifter(lineStorage);

        boolean ascending = OptionReader.getString("Order").equalsIgnoreCase("ascending");
        SorterFactory sorterFactory = new SorterFactory();
        Sorter sorter = sorterFactory.createSorter(shifter, ascending);

        boolean filtering = OptionReader.getString("WordFiltering").equalsIgnoreCase("yes");
        IndexerFactory indexerFactory = new IndexerFactory();
        Indexer indexer = indexerFactory.createIndexer(lineStorage, ascending, filtering);

        if (filtering) {
            ((FilterIndexerDecorator) indexer).setFilterWords(OptionReader.getStringSet("TrivialWords"));
        }

        KeywordSearch keywordSearch = new KeywordSearch(lineStorage);


        String outputFileName = OptionReader.getString("OutputFileName");
        Output output = (Output) OptionReader.getObjectFromStr(OptionReader.getString("Output"));
        output.writeLines(keywordSearch.getKeywordLines("se311"), outputFileName);
    }

}
