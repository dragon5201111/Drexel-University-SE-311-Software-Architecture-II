package process;

import line.LineStorage;

import java.util.*;

public abstract class Indexer {
    private final LineStorage lineStorage;
    private final List<String> indexedWords;
    private final Comparator<String> sortingOrder;

    public Indexer(LineStorage lineStorage, Comparator<String> sortingOrder) {
        this.lineStorage = lineStorage;
        this.indexedWords = new ArrayList<>();
        this.sortingOrder = sortingOrder;
    }

    public List<String> getIndexedWords() {
        if (this.indexedWords.isEmpty()) {
            this.populateIndexedWords();
        }
        return Collections.unmodifiableList(this.indexedWords);
    }

    private void populateIndexedWords() {
        Map<String, Set<Integer>> indexMap = new HashMap<>();

        int lineCount = lineStorage.getLineCount();
        for (int i = 0; i < lineCount; i++) {
            int wordCount = lineStorage.getWordCount(i);
            for (int j = 0; j < wordCount; j++) {
                String word = lineStorage.getWord(i, j);
                indexMap.computeIfAbsent(word, k -> new HashSet<>()).add(i + 1);
            }
        }

        indexMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(this.sortingOrder))
                .forEach(entry -> {
                    String formatted = String.format("%-25s %s", entry.getKey(), entry.getValue());
                    this.indexedWords.add(formatted);
                });
    }
}
