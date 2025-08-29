package shadow.commands;

import shadow.storage.Storage;
import shadow.ui.Ui;

public class ListTasks extends Command {
    private static ListTasks instance;

    private ListTasks() {
    }

    @Override
    public void execute() {
        for (int i = 0; i < Storage.getInstance().getTasks().size(); ++i) {
            Ui.getInstance().println(String.format("%d: %s", i + 1, Storage.getInstance().getTasks().get(i)));
        }
    }

    /**
     * Returns the singleton instance of the {@code ListTasks} command.
     * <p>
     * Creates a new instance if it does not already exist.
     * </p>
     *
     * @param parts the command parts parsed from user input (ignored in this method)
     * @return the singleton {@code ListTasks} instance
     * @throws IllegalArgumentException never thrown by this method but declared for consistency
     */
    public static ListTasks of(String[] parts) throws IllegalArgumentException {
        if (ListTasks.instance == null) {
            ListTasks.instance = new ListTasks();
        }
        return ListTasks.instance;
    }
}
