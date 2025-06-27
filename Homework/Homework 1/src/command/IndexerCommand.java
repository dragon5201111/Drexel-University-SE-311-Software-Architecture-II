package command;

import io.Output;
import process.Indexer;
import java.util.List;

public class IndexerCommand implements Command {
    private final Indexer indexer;
    private final Output output;

    public IndexerCommand(Indexer indexer, Output output) {
        this.indexer = indexer;
        this.output = output;
    }

    @Override
    public void execute() {
        List<String> indexedWords = indexer.getIndexedWords();
        this.output.writeLines(indexedWords);
    }

    @Override
    public String getCommandName() {
        return "Indexer";
    }

    @Override
    public Object getParameter() {
        return null;
    }
}
