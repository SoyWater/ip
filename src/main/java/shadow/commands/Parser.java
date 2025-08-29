package shadow.commands;

import shadow.storage.Storage;
import shadow.tasks.TaskFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Parser {
    private static final Map<String, Function<String[], Command>> commands = new HashMap<>();

    static {
        commands.put("mark", MarkTask::of);
        commands.put("unmark", UnmarkTask::of);
        commands.put("list", ListTasks::of);
        commands.put("delete", DeleteTask::of);
    }

    /**
     * Parses and processes a user input string to determine whether it is a command
     * or a task creation request.
     * <p>
     * If the input matches a known command (e.g., "delete", "unmark", etc.), it is executed via {@code handleCommand}.
     * Otherwise, the input is passed to {@link TaskFactory#createTask(String[])} to create a new task, which is then
     * added to {@link Storage}.
     * </p>
     *
     * @param demand the raw input string entered by the user
     */
    public static void parse(String demand) {
        if (demand.trim().isEmpty()) return;
        String[] parts = demand.split(" ", 2);
        parts[0] = parts[0].toLowerCase();

        try {
            if (handleCommand(parts)) {
                return;
            }
            Storage.getInstance().addTasks(TaskFactory.createTask(parts));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Attempts to handle the parsed input as a command.
     * <p>
     * If the first part of the input matches a known command keyword, the corresponding
     * command is created and executed.
     * </p>
     *
     * @param parts an array of strings where:
     *              <ul>
     *                  <li>{@code parts[0]} is the command keyword</li>
     *                  <li>{@code parts[1]} (optional) is the command argument</li>
     *              </ul>
     *
     * @return {@code true} if the input was successfully handled as a command,
     *         {@code false} if the keyword is not recognized
     */
    private static boolean handleCommand(String[] parts) {
        Function<String[], Command> command = commands.get(parts[0]);
        if (command == null) {
            return false;
        }
        command.apply(parts).execute();
        return true;
    }
}
