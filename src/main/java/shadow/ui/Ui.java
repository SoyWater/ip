package shadow.ui;

import java.util.Scanner;

/**
 * The {@code Ui} class provides an interface for interacting with the user through console input and output.
 * It is implemented as a singleton to ensure only one instance is created and used throughout the application.
 * This class manages user input, displays messages, and handles the formatting of console output.
 */
public class Ui {
    private static Ui ui;
    private final Scanner userInput;

    /**
     * Private constructor for the Ui class.
     * Initializes the user input scanner.
     * <p>
     * This constructor is private to enforce a singleton pattern or controlled instantiation.
     */
    private Ui() {
        this.userInput = new Scanner(System.in);
    }

    /**
     * Returns the singleton instance of the {@code Ui} class.
     * <p>
     * Creates a new instance if it does not already exist.
     * </p>
     * @return the singleton {@code Ui} instance
     */
    public static Ui getInstance() {
        if (Ui.ui == null) {
            Ui.ui = new Ui();
        }
        return Ui.ui;
    }

    /**
     * Prints a line of text to the standard output.
     *
     * @param line the string to be printed
     */
    public void println(String line) {
        System.out.println(line);
    }

    /**
     * Reads a line of input from the user.
     *
     * @return the user's input as a string
     */
    public String readDemand() {
        return userInput.nextLine();
    }

    /**
     * Closes the input reader to release associated resources.
     */
    public void closeReader() {
        userInput.close();
    }

    /**
     * Prints a greeting message along with ASCII art to the console.
     * Prompts the user for input afterward.
     */
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

    /**
     * Prints a separator line to the console.
     */
    public void printLine() {
        System.out.println("_________________________________________________________");
    }

    /**
     * Prints a farewell message to the console.
     */
    public void sayBye() {
        String sayBye = "Very well, contact me again when you have more demands.";
        System.out.println(sayBye);
    }

    /**
     * Prints the command prompt symbol ("> ") to the console.
     */
    public void printCommandInput() {
        System.out.print("> ");
    }

    /**
     * Prints an error message to the standard output.
     *
     * @param error the error message to be printed
     */
    public void printError(String error) {
        System.out.println(error);
    }
}
