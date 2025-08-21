import java.util.Scanner;

public class Shadow {
    public static void main(String[] args) {
        String divider = "_________________________________________________________";
        String asciiArt =
                """
                         ____  _               _              \s
                        / ___|| |__   __ _  __| | _____      __
                        \\___ \\| '_ \\ / _` |/ _` |/ _ \\ \\ /\\ / /
                         ___) | | | | (_| | (_| | (_) \\ V  V /\s
                        |____/|_| |_|\\__,_|\\__,_|\\___/ \\_/\\_/ \s\n
                """;
        String sayHello = "Hello, this is \n" + asciiArt + "What's your demand today?";
        String sayBye = "Very well, contact me again when you have more demands.";


        System.out.println(divider);
        System.out.println(sayHello);

        Scanner userInput = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println(divider);
            System.out.print("> ");
            String demand = userInput.nextLine();
            if (demand.equalsIgnoreCase("bye")) {
                System.out.println(sayBye);
                running = false;
            } else {
                System.out.println(demand);
            }
        }
        userInput.close();
    }
}
