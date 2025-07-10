package process;

import line.LineStorage;

import java.util.List;

public abstract class IndexerDecorator extends Indexer {
    private final Indexer indexer;

    public IndexerDecorator(LineStorage lineStorage, Indexer indexer) {
        super(lineStorage);
        this.indexer = indexer;
    }

    @Override
    public List<String> getIndexedWords() {
        return this.indexer.getIndexedWords();
    }

}
