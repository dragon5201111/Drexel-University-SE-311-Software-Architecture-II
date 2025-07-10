package process;

import line.LineStorage;

import java.util.Comparator;

public class GenericIndexer extends Indexer {
    public GenericIndexer(LineStorage lineStorage, Comparator<String> sortingOrder) {
        super(lineStorage, sortingOrder);
    }
}
