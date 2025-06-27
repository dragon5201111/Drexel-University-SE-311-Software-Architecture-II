package command;

import io.Output;
import process.KeywordSearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KeywordSearchCommand implements Command {
    private final KeywordSearch keywordSearch;
    private final Output output;

    public KeywordSearchCommand(KeywordSearch keywordSearch, Output output) {
        this.keywordSearch = keywordSearch;
        this.output = output;
    }

    @Override
    public void execute() {
        List<String> resultLines;
        List<String> searchResults = keywordSearch.getKeywordLines((String) this.getParameter());

        if (searchResults.isEmpty()){
            resultLines = new ArrayList<>();
            resultLines.add("[keyword] not found.");
            this.output.writeLines(resultLines);
            return;
        }

        resultLines = new ArrayList<>(searchResults);
        resultLines.addFirst(resultLines.size() + " sentence is found:");
        this.output.writeLines(resultLines);
    }

    @Override
    public String getCommandName() {
        return "KeywordSearch";
    }

    @Override
    public Object getParameter() {
        System.out.print("Enter a keyword: ");
        return (new Scanner(System.in)).next();
    }
}
