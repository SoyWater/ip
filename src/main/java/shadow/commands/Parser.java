package shadow.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import shadow.storage.Storage;
import shadow.tasks.TaskFactory;


/**
 * The {@code Parser} class is responsible for interpreting user input and deciding
 * whether it represents a command to be executed or a task to be created. This class
 * serves as the primary mechanism for converting user input into executable actions
 * or new tasks within the application.
 */
public class Parser {
    private static final Map<String, Function<String[], Command>> commands = new HashMap<>();

    static {
        commands.put("mark", MarkTask::of);
        commands.put("unmark", UnmarkTask::of);
        commands.put("list", ListTasks::of);
        commands.put("delete", DeleteTask::of);
        commands.put("find", FindTask::of);
        commands.put("todo", CreateToDo::of);
        commands.put("event", CreateEvent::of);
        commands.put("deadline", CreateDeadLine::of);
        commands.put("bye", parts -> new TerminateCommand());
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
    public static Command parse(String demand) {
        if (demand.trim().isEmpty()) {
            return UnknownCommand.getInstance();
        }
        String[] parts = demand.split(" ", 2);
        parts[0] = parts[0].toLowerCase();

        Function<String[], Command> command = commands.get(parts[0]);
        if (command == null) {
            return UnknownCommand.getInstance();
        }
        return command.apply(parts);
    }
}
