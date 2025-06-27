package command;

public interface Command {
    void execute();
    String getCommandName();
    Object getParameter();
}
