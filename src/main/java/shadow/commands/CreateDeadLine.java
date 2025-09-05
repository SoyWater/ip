package shadow.commands;

import shadow.storage.Storage;
import shadow.tasks.DeadLine;
import shadow.tasks.Event;

public class CreateDeadLine extends Command {
    private DeadLine deadline;

    private CreateDeadLine(DeadLine deadline) {
        this.deadline = deadline;
    }

    @Override
    public String execute() {
        Storage.getInstance().addTask(this.deadline);
        return "Added:\n" + deadline.toString();
    }

    public static CreateDeadLine of(String[] parts) {
        if (parts.length != 2) {
            throw new IllegalArgumentException("Usage: deadline <taskName> /by <by>");
        }
        return new CreateDeadLine(DeadLine.of(parts[1]));

    }
}
