import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class TaskFactory {
    private static final Map<String, Function<String, Task>> taskCreators = new HashMap<>();

    static {
        taskCreators.put("todo", ToDo::of);
        taskCreators.put("deadline", DeadLine::of);
        taskCreators.put ("event", Event::of);
    }

    public static Task createTask(String[] parts) {
        if (parts.length < 2) {
            throw new IllegalArgumentException("Unknown Command");
        }
        String type = parts[0];
        Function<String, Task> factory = taskCreators.get(type);
        if (factory == null) {
            throw new IllegalArgumentException("Unknown Command");
        }
        return factory.apply(parts[1]);
    }
}
