package shadow.commands;

import shadow.tasks.Event;

public class CreateEvent extends Command {
    private Event event;

    private CreateEvent() {

    }

    @Override
    public String execute() {
        return "Added:\n" + event.toString();
    }

    public static CreateEvent of(String[] parts) {
        return new CreateEvent();
    }
}
