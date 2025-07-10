package process;

import line.LineStorage;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FilterIndexerDecorator extends IndexerDecorator {
    private final Set<String> filterWords;

    public FilterIndexerDecorator(LineStorage lineStorage, Indexer indexer, List<String> filterWords) {
        super(lineStorage, indexer);
        this.filterWords = filterWords.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
    }


    @Override
    public List<String> getIndexedWords() {
        List<String> indexedWords = super.getIndexedWords();
        return indexedWords.stream()
                .filter(word -> this.filterWords
                        .stream()
                        .noneMatch(filterWord -> word.toLowerCase().contains(filterWord)))
                .collect(Collectors.toList());
    }

}
