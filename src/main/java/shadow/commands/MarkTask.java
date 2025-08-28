package shadow.commands;

import shadow.storage.Storage;

public class MarkTask extends Command {
    private final int taskIndex;

    private MarkTask(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() {
        Storage.getInstance().markTask(this.taskIndex);
    }

    public static MarkTask of(String[] parts) throws IllegalArgumentException {
        if (parts.length != 2) {
            throw new IllegalArgumentException("Usage: mark <shadow.tasks.Task Number>");
        }
        try {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= Storage.getInstance().getTasks().size()) {
                throw new IllegalArgumentException("You have inputted an invalid task number");
            }
            return new MarkTask(taskIndex);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Usage: mark <shadow.tasks.Task Number>");
        }
    }
}
