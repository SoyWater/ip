package shadow.commands;

import shadow.storage.Storage;
import shadow.tasks.Task;

public class DeleteTask extends Command {
    private final int taskIndex;

    public DeleteTask(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() {
        Task removed = Storage.getInstance().removeTask(this.taskIndex);
        System.out.printf("Removed: %s%n", removed.toString());
    }

    public static DeleteTask of(String[] parts) throws IllegalArgumentException {
        if (parts.length != 2) {
            throw new IllegalArgumentException("Usage: delete <shadow.tasks.Task Number>");
        } else {
            try {
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                if (taskIndex >= Storage.getInstance().getTasks().size() || taskIndex < 0) {
                    throw new IllegalArgumentException("You have inputted an invalid task number");
                }
                return new DeleteTask(taskIndex);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Usage: delete <shadow.tasks.Task Number>");
            }
        }
    }
}
