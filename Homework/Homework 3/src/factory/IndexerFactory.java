package factory;

import line.LineStorage;
import process.FilterIndexerDecorator;
import process.GenericIndexer;
import process.Indexer;

import java.util.Comparator;

public class IndexerFactory {
    public Indexer createIndexer(LineStorage lineStorage, boolean ascending, boolean hasFilters) {
        Indexer indexer;
        Comparator<String> sortingOrder = ascending ? String.CASE_INSENSITIVE_ORDER : String.CASE_INSENSITIVE_ORDER.reversed();
        indexer = new GenericIndexer(lineStorage, sortingOrder);

        if(hasFilters){
            indexer = new FilterIndexerDecorator(lineStorage, indexer, sortingOrder);
        }

        return indexer;
    }
}
