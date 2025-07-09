package io;

import line.LineStorage;
import java.io.File;
import java.util.Scanner;
import java.util.List;

public abstract class Input {
    public void readLines(String fileName, LineStorage lineStorage){
        try{
            Scanner scanner = new Scanner(new File(fileName));
            for(int lineNumber = 0; scanner.hasNextLine(); lineNumber++){
                String rawText = scanner.nextLine();
                List<String> parsedWords = this.parseWords(rawText);

                this.setWordDelimiter(lineStorage);
                this.addWords(lineNumber, parsedWords, lineStorage);
            }
        }catch (Exception _){
        }
    }

    private void addWords(int lineNumber, List<String> parsedWords, LineStorage lineStorage){
        for(String word : parsedWords){
            lineStorage.addWord(lineNumber, word);
        }
    }

    abstract void setWordDelimiter(LineStorage lineStorage);
    abstract List<String> parseWords(String text);
}
