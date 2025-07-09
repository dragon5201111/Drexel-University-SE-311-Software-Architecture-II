package process;

import line.LineStorage;

import java.util.ArrayList;
import java.util.List;

public class Shifter {
    private final LineStorage lineStorage;
    private final List<String> shiftedLines;

    public Shifter(LineStorage lineStorage) {
        this.lineStorage = lineStorage;
        this.shiftedLines = new ArrayList<>();
    }

    public List<String> getShiftedLines() {
        if(this.shiftedLines.isEmpty()) {
            this.populateShiftedLines();
        }
        return this.shiftedLines;
    }

    private void populateShiftedLines() {
        int lineCount = this.lineStorage.getLineCount();
        for(int i = 0; i < lineCount; i++) {
            int wordCount = this.lineStorage.getWordCount(i);

            for (int j = 0; j < wordCount; j++) {
                StringBuilder shiftedLine = new StringBuilder();

                for (int k = 0; k < wordCount; k++) {
                    String word = this.lineStorage.getWord(i, (j + k) % wordCount);
                    shiftedLine.append(word).append(" ");
                }

                this.shiftedLines.add(shiftedLine + " [" + (i + 1) + "]");
            }

        }
    }

}
