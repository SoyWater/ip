public class ToDo extends Task {

    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    public static ToDo of(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Usage: todo <taskName>");
        }
        ToDo todo = new ToDo(input.trim());
        System.out.printf("Added: %s%n", todo.toString());
        return todo;
    }

}
