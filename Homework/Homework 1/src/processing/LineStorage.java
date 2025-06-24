package processing;

import io.Line;

import java.util.List;

public class LineStorage {
    private List<Line> lines;

    public LineStorage(List<Line> lines) {
        this.lines = lines;
    }

    List<Line> getLines() {
        return this.lines;
    }
}
