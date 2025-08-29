package shadow.tasks;

public class Task {
    private final String name;
    private boolean isMarked = false;

    public Task(String name) {
        this.name = name;
    }

    /**
     * Marks the task as completed.
     * <p>
     * Sets the {@code isMarked} flag to {@code true} and prints a confirmation
     * message to the console, including the task's string representation.
     * </p>
     */
    public void mark() {
        System.out.println("Noted, the following task has been marked:");
        this.isMarked = true;
        System.out.println(this.toString());
    }

    /**
     * Unmarks the task (marks it as not completed).
     * <p>
     * Sets the {@code isMarked} flag to {@code false} and prints a confirmation
     * message to the console, including the task's string representation.
     * </p>
     */
    public void unmark() {
        System.out.println("Noted, the following task has been unmarked:");
        this.isMarked = false;
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] %s",
                this.isMarked ? "X" : " ",
                this.name
        );
    }


}
