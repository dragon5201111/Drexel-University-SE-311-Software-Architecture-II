package process;

import line.LineStorage;

import java.util.*;

public abstract class Indexer {
    private final LineStorage lineStorage;
    private final List<String> indexedWords;

    public Indexer(LineStorage lineStorage) {
        this.lineStorage = lineStorage;
        this.indexedWords = new ArrayList<>();
    }

    public List<String> getIndexedWords() {
        if(this.indexedWords.isEmpty()) {
            this.populateIndexedWords();
        }

        return this.indexedWords;
    }

    private void populateIndexedWords() {
        Map<String, Set<Integer>> indexedWords = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        int lineCount = this.lineStorage.getLineCount();

        for(int i = 0; i < lineCount; i++) {
            int wordCount = this.lineStorage.getWordCount(i);

            for(int j = 0; j < wordCount; j++) {
                String word = this.lineStorage.getWord(i, j);
                indexedWords.computeIfAbsent(word, k -> new HashSet<>());
                indexedWords.get(word).add(i+1);
            }
        }

        for(Map.Entry<String, Set<Integer>> entry : indexedWords.entrySet()) {
            String word = entry.getKey();
            Set<Integer> indexes = entry.getValue();

            this.indexedWords.add(String.format("%-25s %s", word, indexes));
        }
    }

}
