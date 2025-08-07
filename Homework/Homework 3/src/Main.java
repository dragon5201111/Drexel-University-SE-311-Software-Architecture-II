
import factory.IndexerFactory;
import factory.SorterFactory;
import io.Input;
import io.Output;
import line.LineStorage;
import object.OptionReader;
import process.*;

import java.util.List;

public class Main {

    private final Input input;
    private final Output output;
    private final SorterFactory sorterFactory;
    private final IndexerFactory indexerFactory;

    public Main(Input input, Output output,
                SorterFactory sorterFactory, IndexerFactory indexerFactory) {
        this.input = input;
        this.output = output;
        this.sorterFactory = sorterFactory;
        this.indexerFactory = indexerFactory;
    }

    public void run(String[] args) {
        String operation = args[0];
        LineStorage lineStorage = getLinePopulatedStorage();

        boolean ascendingSort = OptionReader.getString("Order").equalsIgnoreCase("ascending");

        List<String> outputLines = switch (operation.toLowerCase()) {
            case "kwic-processing" -> getKwicLines(lineStorage, ascendingSort);
            case "keyword-search" -> getKeywordLines(lineStorage, args[1]);
            case "index-generation" -> getIndexedLines(lineStorage, ascendingSort);
            default -> throw new IllegalArgumentException("Unknown operation: " + operation);
        };

        outputLines(outputLines);
    }

    private LineStorage getLinePopulatedStorage() {
        String inputFileName = OptionReader.getString("InputFileName");
        LineStorage lineStorage = new LineStorage();
        this.input.readLines(inputFileName, lineStorage);

        return lineStorage;
    }

    private void outputLines(List<String> lines) {
        String outputFileName = OptionReader.getString("OutputFileName");
        this.output.writeLines(lines, outputFileName);
    }

    private List<String> getKwicLines(LineStorage lineStorage, boolean ascending) {
        Shifter shifter = new Shifter(lineStorage);
        Sorter sorter = this.sorterFactory.createSorter(shifter, ascending);
        return sorter.getSortedLines();
    }

    private List<String> getKeywordLines(LineStorage lineStorage, String keyword) {
        KeywordSearch keywordSearch = new KeywordSearch(lineStorage);
        return keywordSearch.getKeywordLines(keyword);
    }

    private List<String> getIndexedLines(LineStorage lineStorage, boolean ascendingSort) {
        boolean filtering = OptionReader.getString("WordFiltering").equalsIgnoreCase("yes");
        Indexer indexer = this.indexerFactory.createIndexer(lineStorage, ascendingSort, filtering);

        if (filtering && indexer instanceof FilterIndexerDecorator decorator) {
            decorator.setFilterWords(OptionReader.getStringSet("TrivialWords"));
        }

        return indexer.getIndexedWords();
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("You need at least two arguments.");
        }

        OptionReader.readOptions(args[args.length - 1]);

        Input input = (Input) OptionReader.getObjectFromStr(OptionReader.getString("Input"));
        Output output = (Output) OptionReader.getObjectFromStr(OptionReader.getString("Output"));

        SorterFactory sorterFactory = new SorterFactory();
        IndexerFactory indexerFactory = new IndexerFactory();

        Main processor = new Main(input, output, sorterFactory, indexerFactory);
        processor.run(args);
    }
}

