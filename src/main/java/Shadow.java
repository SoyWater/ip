import shadow.commands.Command;
import shadow.commands.Parser;
import shadow.ui.Ui;

/**
 * The Shadow class serves as the entry point for the application.
 * It provides an interactive console environment where users can issue commands
 * to perform various operations. User input is managed through the {@code Ui} singleton instance,
 * and command processing is handled by the {@code Parser} class.
 * This class maintains a loop that continuously accepts user commands, processes them, and provides
 * responses or appropriate error messages until an exit command is issued.
 */
public class Shadow {
    public static void main(String[] args) {
        while (true) {
            try {
                Ui.getInstance().printLine();
                Ui.getInstance().printCommandInput();
                String demand = Ui.getInstance().readDemand();
                if (demand.toLowerCase().startsWith("bye")) {
                    Ui.getInstance().sayBye();
                    break;
                }
                Parser.parse(demand);
            } catch (IllegalArgumentException e) {
                Ui.getInstance().printError(e.getMessage());
            }
        }
        Ui.getInstance().closeReader();
    }

    /**
     * Processes a user's input demand string, parses it into a command, and generates an appropriate response.
     *
     * @param demand the user's input string representing a command and its arguments
     * @return a response string based on the parsed command
     */
    public String getResponse(String demand) {
        Command command = Parser.parse(demand);
        return "hi";
    }
}

