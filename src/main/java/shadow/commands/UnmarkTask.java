package shadow.commands;

import shadow.storage.Storage;

public class UnmarkTask extends Command {

    private final int taskIndex;

    private UnmarkTask(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() {
        Storage.getInstance().unmarkTask(this.taskIndex);
    }

    public static UnmarkTask of(String[] parts) throws IllegalArgumentException {
        if (parts.length != 2) {
            throw new IllegalArgumentException("Usage: unmark <shadow.tasks.Task Number>");
        } else {
            try {
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                if (taskIndex >= Storage.getInstance().getTasks().size()) {
                    throw new IllegalArgumentException("You have inputted an invalid task number");
                }
                return new UnmarkTask(taskIndex);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Usage: unmark <shadow.tasks.Task Number>");
            }
        }
    }
}
