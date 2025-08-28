public class Shadow {
    public static void main(String[] args) {
        Ui.getInstance().sayHello();
        while (true) {
            Ui.getInstance().printLine();
            Ui.getInstance().printCommandInput();
            String demand = Ui.getInstance().readDemand();
            if (demand.toLowerCase().startsWith("bye")) {
                Ui.getInstance().sayBye();
                break;
            }
            Parser.parse(demand);
        }
        Ui.getInstance().closeReader();
    }
}

