package shadow.commands;

import shadow.tasks.ToDo;

public class CreateToDo {
    private ToDo todo;

    private Todo() {

    }

    public String execute() {
        return "Added:\n" + this.todo.toString();
    }

    public static CreateToDo of(String[] parts) {
        return new CreateToDo();
    }
}
