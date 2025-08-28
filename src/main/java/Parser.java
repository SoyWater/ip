import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class Parser {
    private static final Map<String, Function<String[], Command>> commands = new HashMap<>();

    static {
        commands.put("mark", MarkTask::of);
        commands.put("unmark", UnmarkTask::of);
        commands.put("list", ListTasks::of);
        commands.put("delete", DeleteTask::of);
    }

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

    private static boolean handleCommand(String[] parts) {
        Function<String[], Command> command = commands.get(parts[0]);
        if (command == null) {
            return false;
        }
        command.apply(parts).execute();
        return true;
    }
}
