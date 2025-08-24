import java.util.*;
import java.util.function.Consumer;

public class Shadow {

    private final static List<Task> TaskList = new ArrayList<>();
    private static final Map<String, Consumer<String[]>> commands = new HashMap<>();

    static {
        commands.put("mark", Shadow::markTask);
        commands.put("unmark", Shadow::unmarkTask);
        commands.put("list", Shadow::listTasks);
        commands.put("delete", Shadow::deleteTask);
    }
    public static void main(String[] args) {
        Runnable printDivider = () -> System.out.println("_________________________________________________________");
        String asciiArt =
                """
                         ____  _               _
                        / ___|| |__   __ _  __| | _____      __
                        \\___ \\| '_ \\ / _` |/ _` |/ _ \\ \\ /\\ / /
                         ___) | | | | (_| | (_| | (_) \\ V  V /
                        |____/|_| |_|\\__,_|\\__,_|\\___/ \\_/\\_/
                
                """;
        String sayHello = "Hello, this is\n" + asciiArt + "What's your demand today?";
        String sayBye = "Very well, contact me again when you have more demands.";
        printDivider.run();
        System.out.println(sayHello);

        Scanner userInput = new Scanner(System.in);
        while (true) {
            printDivider.run();
            System.out.print("> ");

            String demand = userInput.nextLine();
            if (demand.trim().isEmpty()) continue;
            String[] parts = demand.split(" ", 2);
            parts[0] = parts[0].toLowerCase();

            if (parts[0].equals("bye")) {
                System.out.println(sayBye);
                break;
            }
            try {
                if (handleCommand(parts)) {
                    continue;
                }
                TaskList.add(TaskFactory.createTask(parts));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

        }
        userInput.close();
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
                Task removed = TaskList.remove(Integer.parseInt(parts[1]) - 1);
                System.out.printf("Removed: %s%n", removed.toString());
            } catch (NumberFormatException e) {
                System.out.println("Usage: delete <Task Number>");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("You have inputted an invalid task number");
            }
        }

    }

    private static void listTasks(String[] parts) {
        for (int i = 0; i < TaskList.size(); ++i) {
            System.out.printf("%d: %s%n", i + 1, TaskList.get(i));
        }
    }

    private static void markTask(String[] parts) {
        if (parts.length != 2) {
            System.out.println("Usage: mark <Task Number>");
        } else {
            try {
                TaskList.get(Integer.parseInt(parts[1]) - 1).mark();
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
                TaskList.get(Integer.parseInt(parts[1]) - 1).unmark();
            } catch (NumberFormatException e) {
                System.out.println("Usage: unmark <Task Number>");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("You have inputted an invalid task number");
            }
        }
    }
}

