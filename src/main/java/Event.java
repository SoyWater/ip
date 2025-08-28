import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    public Event(String name, LocalDateTime startTime, LocalDateTime endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format(
                "[E]%s (from: %s to: %s)",
                super.toString(),
                DateTimeParser.format(this.startTime),
                DateTimeParser.format(this.endTime)
        );
    }

    public static Event of(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Usage: event <taskName> /from <from> /to <to>");
        }
        String[] fromSplit = input.split("/from", 2);
        if (fromSplit.length < 2) {
            throw new IllegalArgumentException("Usage: event <taskName> /from <from> /to <to>");
        }
        String[] toSplit = fromSplit[1].split("/to", 2);
        if (toSplit.length < 2) {
            throw new IllegalArgumentException("Usage: event <taskName> /from <from> /to <to>");
        }
        LocalDateTime startTime = DateTimeParser.parse(toSplit[0].trim());
        LocalDateTime endTime = DateTimeParser.parse(toSplit[1].trim());
        Event event = new Event(fromSplit[0].trim(), startTime, endTime);
        System.out.printf("Added: %s%n", event.toString());
        return event;
    }
}
