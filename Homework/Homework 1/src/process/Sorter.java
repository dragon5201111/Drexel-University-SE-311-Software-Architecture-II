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
            this.prependIndexIntoSortedLines();
        }
        return this.sortedLines;
    }

    private void prependIndexIntoSortedLines(){
        int sortedSize = sortedLines.size();
        for(int i = 0; i < sortedSize; i++){
            String currentLine = sortedLines.get(i);
            String formattedLine = String.format("%s %s", String.format("[%07d]", i+1), currentLine);
            this.sortedLines.set(i, formattedLine);
        }
    }
    abstract protected List<String> generateSortedLines(List<String> lines);
}
