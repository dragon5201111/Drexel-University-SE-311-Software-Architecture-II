package processing;

import io.Line;

import java.util.ArrayList;
import java.util.List;

public class CircularShifter {
    public List<Line> generateShiftedLines(List<Line> lines) {
        List<Line> shiftedLines = new ArrayList<>();

        for (Line line : lines) {
            String[] lineTokens = this.getLineTokens(line);

            for (int i = 0; i < lineTokens.length; i++) {
                String shiftedText = buildShiftedText(lineTokens, i);
                shiftedLines.add(new Line(shiftedText, line.getIndex()));
            }
        }

        return shiftedLines;
    }

    private String buildShiftedText(String[] lineTokens, int i) {
        int tokensLength = lineTokens.length;
        StringBuilder stringBuilder = new StringBuilder();

        for (int j = 0; j < tokensLength; j++) {

            stringBuilder.append(lineTokens[(i + j) % tokensLength]);

            if (j < tokensLength - 1) {
                stringBuilder.append(" ");
            }
        }

        return stringBuilder.toString();
    }

    private String[] getLineTokens(Line line){
        return line
                .getText().trim()
                .replaceAll("\\s+", " ")
                .split(" ");
    }

}
