package shadow.tasks;

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
    /**
     * Creates a {@link Task} instance based on the provided input parts.
     * <p>
     * The first element of the {@code parts} array is used to determine the task type.
     * The second element (if present) is used as the task argument passed to the corresponding task factory.
     * </p>
     *
     * @param parts an array of strings where:
     *              <ul>
     *                  <li>{@code parts[0]} is the task type identifier (e.g. "todo", "event")</li>
     *                  <li>{@code parts[1]} (optional) is the task description or argument</li>
     *              </ul>
     *
     * @return a new {@code Task} created by the corresponding factory function
     *
     * @throws IllegalArgumentException if {@code parts[0]} does not match any registered task type
     */
    public static Task createTask(String[] parts) {
        String type = parts[0];
        Function<String, Task> factory = taskCreators.get(type);
        if (factory == null) {
            throw new IllegalArgumentException("Unknown Command");
        }
        return factory.apply(parts.length < 2 ? null : parts[1]);
    }
}
