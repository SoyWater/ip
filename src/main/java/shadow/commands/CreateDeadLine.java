package shadow.commands;

import shadow.tasks.DeadLine;

public class CreateDeadLine extends Command {
    private DeadLine deadline;

    private CreateDeadLine(DeadLine deadline) {
        this.deadline = deadline;
    }

    @Override
    public String execute() {
        return "Added:\n" + deadline.toString();
    }

    public static CreateDeadLine of(String[] parts) {

    }
}
