package command;

import java.util.ArrayList;
import java.util.List;

public class CommandInvoker {
    private final List<Command> commands;

    public CommandInvoker() {
        this.commands = new ArrayList<>();
    }

    public void addCommand(Command command) {
        this.commands.add(command);
    }

    public void execute(int index) {
        this.commands.get(index).execute();
    }

    public int getNumberOfCommands() {
        return this.commands.size();
    }

    public void displayCommands(){
        for (int i = 0; i < this.commands.size(); i++) {
            System.out.println(i + ") " + this.commands.get(i).getCommandName());
        }
    }
}
