public class Shadow {
    public static void main(String[] args) {
        Ui.getInstance().sayHello();
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
}

