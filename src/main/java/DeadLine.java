public class DeadLine extends Task{

    private final String deadline;

    public DeadLine(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }

    public static DeadLine of(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Usage: deadline <taskName> /by <by>");
        }
        String[] deadlineDetails= input.split("/by", 2);
        if (deadlineDetails.length < 2) {
            throw new IllegalArgumentException("Usage: deadline <taskName> /by <by>");
        }
        DeadLine deadline = new DeadLine(deadlineDetails[0].trim(), deadlineDetails[1].trim());
        System.out.printf("Added: %s%n", deadline.toString());
        return deadline;
    }
}
