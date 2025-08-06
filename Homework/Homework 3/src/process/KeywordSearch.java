package process;

import line.LineStorage;

import java.util.*;

public class KeywordSearch {
    private final LineStorage lineStorage;
    private final Map<String, List<String>> keywords;

    public KeywordSearch(LineStorage lineStorage) {
        this.lineStorage = lineStorage;
        this.keywords = new HashMap<>();
    }

    public List<String> getKeywordLines(String keyword) {
        if (this.keywords.isEmpty()) {
            this.populateKeywords();
        }

        return this.keywords.getOrDefault(keyword.toLowerCase(), new ArrayList<>());
    }

    private void populateKeywords() {
        Map<String, Set<String>> tempKeywordMap = new HashMap<>();

        for (int i = 0; i < this.lineStorage.getLineCount(); i++) {
            String line = this.lineStorage.getLine(i);
            Set<String> seenWords = new HashSet<>();

            int wordCount = this.lineStorage.getWordCount(i);
            for (int j = 0; j < wordCount; j++) {
                String word = this.lineStorage.getWord(i, j);
                String lowerWord = word.toLowerCase();

                if (seenWords.contains(lowerWord)) continue;
                seenWords.add(lowerWord);

                String highlighted = this.constructHighlightedLine(line, word);
                tempKeywordMap
                        .computeIfAbsent(lowerWord, k -> new LinkedHashSet<>())
                        .add(highlighted);
            }
        }

        for (Map.Entry<String, Set<String>> entry : tempKeywordMap.entrySet()) {
            this.keywords.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
    }

    private String constructHighlightedLine(String line, String keyword) {
        StringBuilder result = new StringBuilder();
        String lowerLine = line.toLowerCase();
        String lowerKeyword = keyword.toLowerCase();

        int index = 0;
        int matchIndex;

        while ((matchIndex = lowerLine.indexOf(lowerKeyword, index)) != -1) {
            result.append(line, index, matchIndex);
            result.append("[").append(line, matchIndex, matchIndex + keyword.length()).append("]");
            index = matchIndex + keyword.length();
        }

        result.append(line.substring(index));
        return result.toString();
    }
}
