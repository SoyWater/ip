import java.time.LocalDateTime;

public class DeadLine extends Task{

    private final LocalDateTime deadline;

    public DeadLine(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), DateTimeParser.format(this.deadline));
    }

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
