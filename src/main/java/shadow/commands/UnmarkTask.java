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

    /**
     * Creates an instance of {@link UnmarkTask} based on the provided input parts.
     * <p>
     * This factory method expects the input array to contain exactly two elements:
     * the command type (e.g., "unmark") and the task number to unmark. The task number
     * is parsed, validated against the task list in {@link Storage}, and then used
     * to construct the {@code UnmarkTask}.
     * </p>
     *
     * @param parts an array of strings where:
     *              <ul>
     *                  <li>{@code parts[0]} is expected to be the "unmark" command keyword</li>
     *                  <li>{@code parts[1]} is the task number to unmark (1-based index)</li>
     *              </ul>
     *
     * @return a new {@code UnmarkTask} instance with the validated task index
     *
     * @throws IllegalArgumentException if:
     * <ul>
     *     <li>{@code parts.length != 2}</li>
     *     <li>{@code parts[1]} is not a valid number</li>
     *     <li>the task number is out of bounds</li>
     * </ul>
     */
    public static UnmarkTask of(String[] parts) throws IllegalArgumentException {
        if (parts.length != 2) {
            throw new IllegalArgumentException("Usage: unmark <shadow.tasks.Task Number>");
        } else {
            try {
                int taskIndex = Integer.parseInt(parts[1]) - 1;
                if (taskIndex >= Storage.getInstance().getTasks().size() || taskIndex < 0) {
                    throw new IllegalArgumentException("You have inputted an invalid task number");
                }
                return new UnmarkTask(taskIndex);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Usage: unmark <shadow.tasks.Task Number>");
            }
        }
    }
}
