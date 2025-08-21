import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Shadow {

    public static List<Task> TaskList = new ArrayList<>();

    public static void main(String[] args) {
        Runnable printDivider = () -> System.out.println("_________________________________________________________");
        String asciiArt =
                """
                         ____  _               _              \s
                        / ___|| |__   __ _  __| | _____      __
                        \\___ \\| '_ \\ / _` |/ _` |/ _ \\ \\ /\\ / /
                         ___) | | | | (_| | (_| | (_) \\ V  V /\s
                        |____/|_| |_|\\__,_|\\__,_|\\___/ \\_/\\_/ \n
                """;
        String sayHello = "Hello, this is \n" + asciiArt + "What's your demand today?";
        String sayBye = "Very well, contact me again when you have more demands.";
        printDivider.run();
        System.out.println(sayHello);

        Scanner userInput = new Scanner(System.in);
        while (true) {
            printDivider.run();
            System.out.print("> ");
            String demand = userInput.nextLine();
            if (demand.equalsIgnoreCase("bye")) {
                System.out.println(sayBye);
                break;
            } else if (demand.equalsIgnoreCase("list")) {
                listTasks();
            } else {
                addTask(demand);
            }
        }
        userInput.close();
    }

    public static void addTask(String name) {
        TaskList.add(new Task(name));
        System.out.printf("Added: %s", name);
    }

    public static void listTasks() {
        for (int i = 0; i < TaskList.size(); ++i) {
            System.out.printf("%d: %s%n", i + 1, TaskList.get(i));
        }
    }
}

