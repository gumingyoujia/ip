/**
 * The main entry point for the Walkytalky application.
 * Handles user interaction, command parsing, and task management.
 */
public class Walkytalky {
    private static final String FILE_PATH = "./data/tasks.txt";
    private final Storage storage;
    private final Parser parser;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Walkytalky instance, initializing storage, parser, UI, and task list.
     */
    public Walkytalky() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(FILE_PATH);
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the main application loop.
     * Continuously reads user input, parses commands, and executes them.
     * Saves the task list after each valid operation.
     */
    public void run() {
        ui.printWelcomeMessage();
        while (true) {
            String input = ui.readCommand();
            ui.showLine();
            try {
                Command command = Parser.parseCommand(input);
                switch (command) {
                case LIST:
                    tasks.listTasks();
                    break;
                case FIND:
                    String keyword= Parser.getKeyword(input);
                    tasks.searchTask(keyword);
                    break;
                case BYE:
                    ui.showExitMessage();
                    return;
                case UNMARK:
                    int index = Parser.getIndex(input,tasks.size());
                    tasks.getTask(index - 1).unmark();
                    ui.showUnmarkMessage(tasks.getTask(index - 1));
                    break;
                case MARK:
                    index = Parser.getIndex(input,tasks.size());
                    tasks.getTask(index - 1).mark();
                    ui.showMarkMessage(tasks.getTask(index - 1));
                    break;
                case DELETE:
                    Task removed = tasks.deleteTask(Parser.getIndex(input,tasks.size()) - 1);
                    ui.showDeleteMessage(removed);
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    Task newTask = Parser.parseTask(input, command);
                    tasks.addTask(newTask);
                }
                ui.showLine();
                storage.save(tasks.getAll());
            } catch (CommandException | TaskFormatException | TaskIndexException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }
    }

    /**
     * Application entry point.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Walkytalky().run();
    }
}