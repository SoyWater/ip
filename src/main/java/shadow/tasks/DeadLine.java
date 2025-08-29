package shadow.tasks;

import shadow.parsers.DateTimeParser;

import java.time.LocalDateTime;

public class DeadLine extends Task {

    private final LocalDateTime deadline;

    public DeadLine(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        long timeLeft = DateTimeParser.timeLeft(this.deadline);
        return String.format("[D]%s (by: %s): ", super.toString(), DateTimeParser.format(this.deadline)) +
                (timeLeft < 0 ? "deadline passed" : timeLeft + " days left");
    }

    /**
     * Creates a new {@link DeadLine} task from the given input string.
     * <p>
     * Expects the input to contain a task name and a deadline separated by the "/by" delimiter.
     * Parses the deadline date-time using {@link DateTimeParser}.
     * If the input is {@code null} or improperly formatted, throws an {@link IllegalArgumentException}
     * with usage instructions.
     * Upon successful creation, the deadline task is printed to the console.
     * </p>
     *
     * @param input the raw user input containing the task description and deadline, separated by "/by"
     * @return a new {@code DeadLine} instance representing the task with its deadline
     * @throws IllegalArgumentException if the input is {@code null} or missing the "/by" part
     */
    public static DeadLine of(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Usage: deadline <taskName> /by <by>");
        }
        String[] deadlineDetails= input.split("/by", 2);
        if (deadlineDetails.length < 2) {
            throw new IllegalArgumentException("Usage: deadline <taskName> /by <by>");
        }
        LocalDateTime deadlineDateTime = DateTimeParser.parse(deadlineDetails[1].trim());
        DeadLine deadline = new DeadLine(deadlineDetails[0].trim(), deadlineDateTime);
        System.out.printf("Added: %s%n", deadline.toString());
        return deadline;
    }
}
