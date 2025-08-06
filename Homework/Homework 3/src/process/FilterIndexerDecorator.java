package process;

import line.LineStorage;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FilterIndexerDecorator extends IndexerDecorator {
    private Set<String> filterWords;

    public FilterIndexerDecorator(LineStorage lineStorage, Indexer indexer, Comparator<String> sortingOrder) {
        super(lineStorage, indexer,sortingOrder);
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

    public void setFilterWords(Set<String> filterWords) {
        this.filterWords = filterWords.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
    }

}
