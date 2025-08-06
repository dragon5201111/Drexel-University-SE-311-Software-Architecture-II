package line;

import java.util.ArrayList;
import java.util.List;

public class LineStorage {
    private final List<List<String>> lineWords;
    private String wordDelimiter;

    public LineStorage(String wordDelimiter) {
        this.lineWords = new ArrayList<>();
        this.wordDelimiter = wordDelimiter;
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

    public void addLine(int lineNumber, String line) {
        for (String word : line.split(this.wordDelimiter)) {
            this.addWord(lineNumber, word);
        }
    }

    public void setLines(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            this.addLine(i, lines.get(i));
        }
    }

    public String getLine(int lineNumber) {
        return String.join(this.wordDelimiter, this.lineWords.get(lineNumber));
    }
}
