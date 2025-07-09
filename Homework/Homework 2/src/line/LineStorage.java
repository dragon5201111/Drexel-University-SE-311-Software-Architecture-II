package line;

import java.util.ArrayList;
import java.util.List;

public class LineStorage {
    private final List<List<String>> lineWords;
    private String wordDelimiter;

    public LineStorage() {
        this.lineWords = new ArrayList<>();
    }

    public int getLineCount() {
        return this.lineWords.size();
    }

    public void addWord(int lineNumber,String word) {
        while (this.lineWords.size() <= lineNumber) {
            this.lineWords.add(new ArrayList<>());
        }
        this.lineWords.get(lineNumber).add(word);
    }

    public int getWordCount(int lineNumber) {
        return this.lineWords.get(lineNumber).size();
    }

    public String getWord(int lineNumber, int wordIndex) {
        return this.lineWords.get(lineNumber).get(wordIndex);
    }

    public void setWordDelimiter(String wordDelimiter) {
        this.wordDelimiter = wordDelimiter;
    }

    public String getLine(int lineNumber) {
        return String.join(this.wordDelimiter, this.lineWords.get(lineNumber));
    }
}
