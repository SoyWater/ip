package shadow.commands;

public class TerminateCommand extends Command {

    @Override
    public String execute() {
        return "Terminating application";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
