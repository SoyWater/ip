public class Event extends Task {
    private final String startTime;
    private final String endTime;

    public Event(String name, String startTime, String endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.startTime, this.endTime);
    }

    public static Event of(String input) {
        String[] fromSplit = input.split("/from", 2);
        if (fromSplit.length < 2) {
            throw new IllegalArgumentException("Usage: event <taskName> /from <from> /to <to>");
        }
        String[] toSplit = fromSplit[1].split("/to", 2);
        if (toSplit.length < 2) {
            throw new IllegalArgumentException("Usage: event <taskName> /from <from> /to <to>");
        }
        Event event = new Event(fromSplit[0].trim(), toSplit[0].trim(), toSplit[1].trim());
        System.out.printf("Added: %s%n", event.toString());
        return event;
    }
}
