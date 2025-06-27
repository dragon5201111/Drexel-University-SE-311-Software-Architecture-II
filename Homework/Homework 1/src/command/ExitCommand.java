package command;

public class ExitCommand implements Command {
    @Override
    public void execute() {
        System.exit(0);
    }

    @Override
    public String getCommandName() {
        return "Exit";
    }

    @Override
    public Object getParameter() {
        return null;
    }
}
