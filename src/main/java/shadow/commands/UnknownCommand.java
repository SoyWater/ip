package shadow.commands;

public class UnknownCommand extends Command {
    private static final UnknownCommand unknownCommand = new UnknownCommand();

    @Override
    public String execute() {
        return "Unknown command";
    }

    public static UnknownCommand getInstance() {
        return UnknownCommand.unknownCommand;
    }
}
