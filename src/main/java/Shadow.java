import java.util.*;
import java.util.function.Consumer;

public class Shadow {

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
            if (demand.toLowerCase().startsWith("bye")) {
                System.out.println(sayBye);
                break;
            }
            Parser.parse(demand);

        }
        userInput.close();
    }
}

