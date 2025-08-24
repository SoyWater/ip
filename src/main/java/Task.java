public class Task {
    private final String name;
    private boolean isMarked = false;

    public Task(String name) {
        this.name = name;
    }

    public void mark() {
        System.out.println("Noted, the following task has been marked:");
        this.isMarked = true;
        System.out.println(this.toString());
    }

    public void unmark() {
        System.out.println("Noted, the following task has been unmarked:");
        this.isMarked = false;
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] %s",
                this.isMarked ? "X" : " ",
                this.name
        );
    }


}
