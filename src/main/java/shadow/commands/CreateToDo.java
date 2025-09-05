package shadow.commands;

import shadow.storage.Storage;
import shadow.tasks.ToDo;

public class CreateToDo extends Command {
    private final ToDo todo;

    private CreateToDo(ToDo todo) {
        this.todo = todo;
    }

    @Override
    public String execute() {
        Storage.getInstance().addTask(this.todo);
        return "Added:\n" + this.todo.toString();
    }

    public static CreateToDo of(String[] parts) {
        if (parts.length != 2) {
            throw new IllegalArgumentException("Usage: todo <taskName>");
        }
        return new CreateToDo(ToDo.of(parts[1]));
    }


}
