import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Gson gson;
    private final Path filePath;
    private final Type taskListType = new TypeToken<List<Task>>() {}.getType();
    private List<Task> tasks;

    public Storage() {
        String userHome = System.getProperty("user.home");
        Path appDir = Paths.get(userHome, ".shadowData");
        this.filePath = appDir.resolve("tasks.json");

        this.gson = new GsonBuilder()
                .registerTypeAdapterFactory(
                        RuntimeTypeAdapterFactory.of(Task.class, "type")
                                .registerSubtype(ToDo.class, "todo")
                                .registerSubtype(DeadLine.class, "deadline")
                                .registerSubtype(Event.class, "event")
                )
                .setPrettyPrinting()
                .create();
        this.tasks = new ArrayList<>();

        try {
            if (!Files.exists(appDir)) {
                Files.createDirectories(appDir);
            }
        } catch (IOException e) {
            System.err.println("Failed to create storage directory: " + e.getMessage());
        }

        load();
    }

    private void load() {
        if (!Files.exists(filePath)) {
            return;
        }
        try (Reader reader = Files.newBufferedReader(filePath)) {
            List<Task> load = gson.fromJson(reader, taskListType);
            if (load != null) {
                this.tasks = load;
            }
        } catch (IOException e) {
            System.err.println("Failed to load tasks: " + e.getMessage());

        }
    }

    public void save() {
        try (Writer writer = Files.newBufferedWriter(filePath)) {
            gson.toJson(tasks, taskListType, writer);
        } catch (IOException e) {
            System.err.println("Failed to save tasks: " + e.getMessage());
        }
    }

    public void addTasks(Task task) {
        tasks.add(task);
        save();
    }

    public Task removeTask(int i) {
        Task removed = this.tasks.remove(i);
        save();
        return removed;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }
}
