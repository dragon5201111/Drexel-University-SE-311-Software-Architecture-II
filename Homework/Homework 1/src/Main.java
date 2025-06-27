import command.*;
import io.ConsoleOutput;
import io.Input;
import io.Output;
import io.TextInput;
import line.LineStorage;
import process.*;

import java.io.File;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        LineStorage lineStorage = new LineStorage();
        CommandInvoker commandInvoker = Main.getCommandInvoker(lineStorage);
        Main.run(commandInvoker);
    }

    private static CommandInvoker getCommandInvoker(LineStorage lineStorage) {
        Input input = new TextInput();
        input.readLines(Main.getFileName(), lineStorage);
        Output output = new ConsoleOutput();

        KeywordSearch keywordSearch = new KeywordSearch(lineStorage);
        Shifter shifter = new Shifter(lineStorage);
        Sorter sorter = new AlphabeticalSorter(shifter);
        Indexer indexer = new GenericIndexer(lineStorage);

        CommandInvoker commandInvoker = new CommandInvoker();
        commandInvoker.addCommand(new KeywordSearchCommand(keywordSearch, output));
        commandInvoker.addCommand(new SorterCommand(sorter, output));
        commandInvoker.addCommand(new IndexerCommand(indexer, output));

        return commandInvoker;
    }

    private static String getFileName() {
        boolean fileExists = false;
        String fileName;

        do{
            System.out.print("Enter a filename [absolute path]: ");
            fileName = Main.SCANNER.nextLine();
            File file = new File(fileName);

            if (file.exists()){
                fileExists = true;
            }else{
                System.out.println("File does not exist. Try again.");
            }
        }while (!fileExists);

        return fileName;
    }

    private static void run(CommandInvoker commandInvoker) {
        boolean exit;

        do{
            commandInvoker.displayCommands();
            int commandIndex = Main.getInt(commandInvoker.getNumberOfCommands());
            commandInvoker.execute(commandIndex);
            System.out.println();
            exit = Main.exit();
        }while (!exit);
    }
    private static boolean exit(){
        System.out.print("Do you want to exit? (y/n): ");
        return Main.SCANNER.nextLine().trim().equalsIgnoreCase("y");
    }

    private static int getInt(int max) {
        while (true) {
            System.out.print("Select an option: ");
            String input = Main.SCANNER.nextLine();
            try {
                int value = Integer.parseInt(input);
                if (value >= 0 && value < max) {
                    return value;
                } else {
                    System.out.println("Number must be between 0 and " + (max - 1));
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }


}
