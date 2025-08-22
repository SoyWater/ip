import java.util.ArrayList;
import java.util.List;

public class Task {
    private final String name;
    private boolean marked = false;

    public Task(String name) {
        this.name = name;
    }

    public void mark() {
        System.out.println("Noted, the following task has been marked:");
        this.marked = true;
        System.out.println(this.toString());
    }

    public void unmark() {
        System.out.println("Noted, the following task has been unmarked:");
        this.marked = false;
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return String.format(
                "[%s] %s",
                this.marked ? "X" : " ",
                this.name
        );
    }


}
