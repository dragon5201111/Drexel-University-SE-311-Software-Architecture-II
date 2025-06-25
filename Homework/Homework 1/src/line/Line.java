package line;

import java.util.List;

public class Line {
    private List<String> words;
    private int lineNumber;
    private String text;

    public Line(String text, int lineNumber, List<String> words) {
        this.text = text;
        this.lineNumber = lineNumber;
        this.words = words;
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public String getText() {
        return this.text;
    }

    public List<String> getWords() {
        return this.words;
    }
}
