package shadow.commands;

import shadow.storage.Storage;
import shadow.tasks.Task;
import shadow.ui.Ui;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a command that finds and displays tasks matching a given keyword.
 */
public class FindTask extends Command {
    private final List<Task> tasks;

    /**
     * Constructs a FindTask with a filtered list of tasks.
     *
     * @param tasks the list of tasks that match the search keyword
     */
    private FindTask(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Executes the find command.
     * If no tasks are found, it displays a "Found nothing" message.
     * Otherwise, it displays the list of found tasks with their corresponding index.
     */
    @Override
    public void execute() {
        if (tasks.isEmpty()) {
            Ui.getInstance().println("Found nothing");
            return;
        }
        for (int i = 0; i < this.tasks.size(); ++i) {
            Ui.getInstance().println(String.format("%d: %s", i + 1, this.tasks.get(i)));
        }
    }

    /**
     * Creates a new FindTask based on the input command parts.
     *
     * @param parts the parts of the command input, where parts[1] should be the search string
     * @return a FindTask object with filtered tasks
     * @throws IllegalArgumentException if the command format is invalid
     */
    public static FindTask of(String[] parts) throws IllegalArgumentException {
        if (parts.length != 2) {
            throw new IllegalArgumentException("Usage: find <findString>");
        } else {
            try {
                String findString = parts[1];
                return new FindTask(FindTask.filterTasks(Storage.getInstance().getTasks(), findString));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Usage: delete <shadow.tasks.Task Number>");
            }
        }
    }

    /**
     * Filters the given list of tasks by the specified keyword.
     *
     * @param tasks      the list of tasks to filter
     * @param findString the keyword to filter tasks by
     * @return a list of tasks whose names contain the given keyword
     */
    private static List<Task> filterTasks(List<Task> tasks, String findString) {
        return tasks
                .stream()
                .filter(task -> task.contains(findString))
                .collect(Collectors.toList());
    }
}
