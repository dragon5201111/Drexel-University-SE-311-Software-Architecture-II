package io;

import line.LineStorage;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public abstract class Input {
    public void readLines(String filename, LineStorage lineStorage){
        try{
            Scanner scanner = new Scanner(new File(filename));
            for(int i = 0; scanner.hasNextLine(); i++){
                String text = scanner.nextLine();
                List<String> words = this.parseLineText(text);

                lineStorage.addLine(text);
                this.addWordsToLineStorage(i, lineStorage, words);
            }
        }catch (Exception _){
        }
    }

    private void addWordsToLineStorage(int lineNumber,LineStorage lineStorage, List<String> words){
        for(String word : words){
            lineStorage.addWord(lineNumber, word);
        }
    }

    abstract List<String> parseLineText(String text);
}
