package process;

import java.util.ArrayList;
import java.util.List;

public abstract class Sorter {
    protected List<String> sortedLines;
    protected final Shifter shifter;

    public Sorter(Shifter shifter){
        this.shifter = shifter;
        this.sortedLines = new ArrayList<>();
    }

    public List<String> getSortedLines() {
        if(this.sortedLines.isEmpty()){
            this.sortedLines = this.generateSortedLines(shifter.getShiftedLines());
        }
        return this.sortedLines;
    }

    abstract protected List<String> generateSortedLines(List<String> lines);
}
