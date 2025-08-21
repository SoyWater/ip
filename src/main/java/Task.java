import java.util.ArrayList;
import java.util.List;

public class Task {
    private final String name;
    private boolean marked = false;

    public Task(String name) {
        this.name = name;
    }

    public void mark() {
        this.marked = true;
    }

    public void unmark() {
        this.marked = false;
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
