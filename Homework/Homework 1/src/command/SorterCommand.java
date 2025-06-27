package command;

import io.Output;
import process.Sorter;
import java.util.List;

public class SorterCommand implements Command {
    private final Sorter sorter;
    private final Output output;

    public SorterCommand(Sorter sorter, Output output) {
        this.sorter = sorter;
        this.output = output;
    }
    @Override
    public void execute() {
        List<String> sortedLines = this.sorter.getSortedLines();
        this.output.writeLines(sortedLines);
    }

    @Override
    public String getCommandName() {
        return "Sorter (KWIC Processing)";
    }

    @Override
    public Object getParameter() {
        return null;
    }
}
