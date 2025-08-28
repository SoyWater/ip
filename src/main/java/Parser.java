import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Parser {
    private static final Map<String, Consumer<String[]>> commands = new HashMap<>();

    static {
        commands.put("mark", Parser::markTask);
        commands.put("unmark", Parser::unmarkTask);
        commands.put("list", Parser::listTasks);
        commands.put("delete", Parser::deleteTask);
    }

    public static void parse(String demand) {
        if (demand.trim().isEmpty()) return;
        String[] parts = demand.split(" ", 2);
        parts[0] = parts[0].toLowerCase();

        try {
            if (handleCommand(parts)) {
                return;
            }
            Storage.getInstance().addTasks(TaskFactory.createTask(parts));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean handleCommand(String[] parts) {
        Consumer<String[]> command = commands.get(parts[0]);
        if (command == null) {
            return false;
        }
        command.accept(parts);
        return true;
    }

    private static void deleteTask(String[] parts) {
        if (parts.length != 2) {
            System.out.println("Usage: delete <Task Number>");
        } else {
            try {
                Task removed = Storage.getInstance().removeTask(Integer.parseInt(parts[1]) - 1);
                System.out.printf("Removed: %s%n", removed.toString());
            } catch (NumberFormatException e) {
                System.out.println("Usage: delete <Task Number>");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("You have inputted an invalid task number");
            }
        }

    }

    private static void listTasks(String[] parts) {
        for (int i = 0; i < Storage.getInstance().getTasks().size(); ++i) {
            System.out.printf("%d: %s%n", i + 1, Storage.getInstance().getTasks().get(i));
        }
    }

    private static void markTask(String[] parts) {
        if (parts.length != 2) {
            System.out.println("Usage: mark <Task Number>");
        } else {
            try {
                Storage.getInstance().markTask(Integer.parseInt(parts[1]) - 1);
            } catch (NumberFormatException e) {
                System.out.println("Usage: mark <Task Number>");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("You have inputted an invalid task number");
            }
        }
    }

    private static void unmarkTask(String[] parts) {
        if (parts.length != 2) {
            System.out.println("Usage: unmark <Task Number>");
        } else {
            try {
                Storage.getInstance().unmarkTask(Integer.parseInt(parts[1]) - 1);
            } catch (NumberFormatException e) {
                System.out.println("Usage: unmark <Task Number>");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("You have inputted an invalid task number");
            }
        }
    }
}
