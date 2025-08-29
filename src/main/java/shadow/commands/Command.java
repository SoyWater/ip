package shadow.commands;

public abstract class Command {
    /**
     * Executes the main logic or action of this command.
     * This method is abstract and must be implemented by subclasses.
     */
    public abstract void execute();

    /**
     * Indicates whether this command signals an exit condition.
     *
     * @return {@code true} if this command causes the application to exit; {@code false} otherwise.
     */
    public boolean isExit() {
        return false;
    }
}

