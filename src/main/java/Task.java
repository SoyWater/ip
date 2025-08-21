import java.util.ArrayList;
import java.util.List;

public class Task {
    private final String name;
    private boolean marked = false;

    private static List<Task> TaskList = new ArrayList<>();

    public Task(String name) {
        this.name = name;
    }

    public void mark() {
        this.marked = true;
    }

    public void unmark() {
        this.marked = false;
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] %s",
                this.marked ? "X" : " ",
                this.name
        );
    }

    public static void addTask(String name) {
        Task.TaskList.add(new Task(name));
        System.out.printf("Added: %s", name);
    }

    public static void listTasks() {
        for (int i = 0; i < Task.TaskList.size(); ++i) {
            System.out.printf("%d: %s%n", i + 1, Task.TaskList.get(i));
        }
    }

}
