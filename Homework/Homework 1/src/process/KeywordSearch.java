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

    public List<String> getKeywordLines(String keyword){
        if (this.keywords.isEmpty()){
            this.populateKeywords();
        }

        return this.keywords.get(keyword);
    }

    private void populateKeywords(){
        for (int i = 0; i < this.lineStorage.getLineCount(); i++){
            int wordCount = this.lineStorage.getWordCount(i);
            String line = this.lineStorage.getLine(i);

            for (int j = 0; j < wordCount; j++){
                String word = this.lineStorage.getWord(i, j);
                this.keywords
                        .computeIfAbsent(word, k -> new ArrayList<>())
                        .add(line);
            }
        }
    }
}
