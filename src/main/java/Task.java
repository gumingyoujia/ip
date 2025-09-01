class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        System.out.println("Got it. I've added this task:");
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + description;
    }
}