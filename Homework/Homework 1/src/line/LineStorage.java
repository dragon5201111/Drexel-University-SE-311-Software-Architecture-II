package line;

import java.util.ArrayList;
import java.util.List;

public class LineStorage {
    private final List<String> lines;
    private final List<List<String>> lineWords;

    public LineStorage() {
        this.lines = new ArrayList<>();
        this.lineWords = new ArrayList<>();
    }

    public void addLine(String line) {
        this.lines.add(line);
    }

    public int getLineCount() {
        return this.lines.size();
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

}
