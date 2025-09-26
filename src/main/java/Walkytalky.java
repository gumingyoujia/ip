public class Walkytalky {
    private static final String FILE_PATH = "./data/tasks.txt";
    private final Storage storage;
    private final Parser parser;
    private final TaskList tasks;
    private final Ui ui;


    public Walkytalky() {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(FILE_PATH);
        tasks = new TaskList(storage.load());
    }

    private int ensureIndex(String input) throws TaskIndexException {
        String[] parts = input.split("\\s+", 2);
        if (parts.length < 2 || parts[1].isBlank()) {
            throw new TaskIndexException("Please provide a task number.");
        }
        try {
            int index = Integer.parseInt(parts[1].trim());
            if (index < 1 || index > tasks.size()) {
                throw new TaskIndexException("Invalid task number: " + index);
            }
            return index;
        } catch (NumberFormatException e) {
            throw new TaskIndexException("Task number must be an integer.");
        }
    }

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
                case BYE:
                    ui.showExit();
                    return;
                case UNMARK:
                    int index = ensureIndex(input);
                    tasks.getTask(index - 1).unmark();
                    ui.showUnmarkMessage(tasks.getTask(index - 1));
                    break;
                case MARK:
                    index = ensureIndex(input);
                    tasks.getTask(index - 1).mark();
                    ui.showMarkMessage(tasks.getTask(index - 1));
                    break;
                case DELETE:
                    Task removed = tasks.deleteTask(ensureIndex(input) - 1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("   " + removed);
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
                ui.showError(e.getMessage());
            }
        }
    }


    public static void main(String[] args) {
        new Walkytalky().run();
    }
}