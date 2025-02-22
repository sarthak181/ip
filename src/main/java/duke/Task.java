package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws DukeException {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unMark() {
        this.isDone = false;
    }

    public void isDate() {}

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
