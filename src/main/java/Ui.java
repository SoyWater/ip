import java.util.Scanner;

public class Ui {
    private static Ui ui;
    private final Scanner userInput;

    private Ui() {
         this.userInput = new Scanner(System.in);
    }

    public static Ui getInstance() {
        if (Ui.ui == null) {
            Ui.ui = new Ui();
        }
        return Ui.ui;
    }

    public String readDemand() {
        return userInput.nextLine();
    }

    public void closeReader() {
        userInput.close();
    }

    public void sayHello() {
        String asciiArt =
                """
                         ____  _               _
                        / ___|| |__   __ _  __| | _____      __
                        \\___ \\| '_ \\ / _` |/ _` |/ _ \\ \\ /\\ / /
                         ___) | | | | (_| | (_| | (_) \\ V  V /
                        |____/|_| |_|\\__,_|\\__,_|\\___/ \\_/\\_/
                
                """;
        String sayHello = "Hello, this is\n" + asciiArt + "What's your demand today?";
        this.printLine();
        System.out.println(sayHello);
    }

    public void printLine() {
        System.out.println("_________________________________________________________");
    }

    public void sayBye() {
        String sayBye = "Very well, contact me again when you have more demands.";
        System.out.println(sayBye);
    }

    public void printCommandInput() {
        System.out.print("> ");
    }
}
