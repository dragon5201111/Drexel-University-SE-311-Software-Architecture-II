package line;

import java.util.ArrayList;
import java.util.List;

public class LineStorage {
    private List<Line> lines;

    public LineStorage() {
        this.lines = new ArrayList<>();
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public List<Line> getLines() {
        return this.lines;
    }

}
