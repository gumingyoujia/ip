/**
 * The main entry point for the Walkytalky application.
 * Handles user interaction, command parsing, and task management.
 */
public class Walkytalky {
    private static final String FILE_PATH = "./data/tasks.txt";
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Constructs a Walkytalky instance, initializing storage, parser, UI, and task list.
     */
    public Walkytalky() {
        ui = new Ui();
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
        boolean exit = false;
        while (!exit) {
            String input = ui.readCommand();
            ui.showLine();
            try {
                Command command = Parser.parseCommand(input);
                exit=command.execute(input, tasks, ui, storage);
            } catch (Exception e) {
                ui.showErrorMessage(e.getMessage());
            }
            ui.showLine();
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