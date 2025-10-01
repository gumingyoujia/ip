public enum Command {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    MARK("mark"),
    UNMARK("unmark"),
    LIST("list"),
    DELETE("delete"),
    FIND ("find"),
    BYE("bye");

    private final String keyword;

    Command(String keyword) {
        this.keyword = keyword;
    }


    public static Command fromInput(String input) {
        for (Command c : values()) {
            if (input.startsWith(c.keyword)) {
                return c;
            }
        }
        return null; // unknown command
    }

    public int getLength(){
        return keyword.length();
    }

    /**
     * Executes the given command with the provided context.
     *
     * @param input  The full user input string.
     * @param tasks  The task list to modify or read from.
     * @param ui     The UI to interact with the user.
     * @param storage The storage to save tasks persistently.
     * @throws Exception if command execution fails (e.g., invalid input).
     */
    public boolean execute(String input, TaskList tasks, Ui ui, Storage storage) throws Exception {
        switch (this) {
        case LIST:
            tasks.listTasks();
            break;
        case FIND:
            String word = Parser.getKeyword(input);
            tasks.searchTask(word);
            break;
        case BYE:
            ui.showExitMessage();
            return true;
        case MARK:
            int markIndex = Parser.getIndex(input, tasks.size());
            tasks.getTask(markIndex - 1).mark();
            ui.showMarkMessage(tasks.getTask(markIndex - 1));
            break;
        case UNMARK:
            int unmarkIndex = Parser.getIndex(input, tasks.size());
            tasks.getTask(unmarkIndex - 1).unmark();
            ui.showUnmarkMessage(tasks.getTask(unmarkIndex - 1));
            break;
        case DELETE:
            int delIndex = Parser.getIndex(input, tasks.size());
            Task removed = tasks.deleteTask(delIndex - 1);
            ui.showDeleteMessage(removed);
            break;
        case TODO:
        case DEADLINE:
        case EVENT:
            Task newTask = Parser.parseTask(input, this);
            tasks.addTask(newTask);
            break;
        }
        // Save after every successful command
        storage.save(tasks.getAll());
        return false;
    }
}
