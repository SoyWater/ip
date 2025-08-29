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

    /**
     * Creates a new {@code MarkTask} command from the given input parts.
     * <p>
     * Expects exactly two parts: the command and the task number.
     * Parses the task number (1-based) and converts it to a zero-based index.
     * Validates the index against the current list of tasks in {@link Storage}.
     * Throws an {@link IllegalArgumentException} if the input is malformed or the index is invalid.
     * </p>
     *
     * @param parts the command parts parsed from user input
     * @return a new {@code MarkTask} instance targeting the specified task index
     * @throws IllegalArgumentException if the input format is invalid or the task number is out of range
     */
    public static MarkTask of(String[] parts) throws IllegalArgumentException {
        if (parts.length != 2) {
            throw new IllegalArgumentException("Usage: mark <shadow.tasks.Task Number>");
        }
        try {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= Storage.getInstance().getTasks().size() || taskIndex < 0) {
                throw new IllegalArgumentException("You have inputted an invalid task number");
            }
            return new MarkTask(taskIndex);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Usage: mark <shadow.tasks.Task Number>");
        }
    }
}
