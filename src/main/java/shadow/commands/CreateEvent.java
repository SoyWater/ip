package shadow.commands;

import shadow.storage.Storage;
import shadow.tasks.Event;

public class CreateEvent extends Command {
    private final Event event;

    private CreateEvent(Event event) {
        this.event = event;
    }

    @Override
    public String execute() {
        Storage.getInstance().addTask(this.event);
        return "Added:\n" + event.toString();
    }

    public static CreateEvent of(String[] parts) {
        if (parts.length != 2) {
            throw new IllegalArgumentException("Usage: event <taskName> /from <from> /to <to>");
        }
        return new CreateEvent(Event.of(parts[1]));
    }
}
