import java.util.Scanner;
import java.util.ArrayList;

public class Walkytalky {
    private static final String FILE_PATH = "./data/tasks.txt";
    private static final String HLINE = "—".repeat(60)+'\n';
    private static final String LOGO =
            "▗▖ ▗▖ ▗▄▖ ▗▖   ▗▖ ▗▖▗▖  ▗▖▗▄▄▄▖▗▄▖ ▗▖   ▗▖ ▗▖▗▖  ▗▖\n" +
            "▐▌ ▐▌▐▌ ▐▌▐▌   ▐▌▗▞▘ ▝▚▞▘   █ ▐▌ ▐▌▐▌   ▐▌▗▞▘ ▝▚▞▘ \n" +
            "▐▌ ▐▌▐▛▀▜▌▐▌   ▐▛▚▖   ▐▌    █ ▐▛▀▜▌▐▌   ▐▛▚▖   ▐▌  \n" +
            "▐▙█▟▌▐▌ ▐▌▐▙▄▄▖▐▌ ▐▌  ▐▌    █ ▐▌ ▐▌▐▙▄▄▖▐▌ ▐▌  ▐▌  \n" +
            "                                                   ";

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final Storage storage = new Storage(FILE_PATH);

    public static void printWelcomeMessage() {
        System.out.print(HLINE);
        System.out.println(" Hi!!! I am your\n" + LOGO);
        System.out.println(" You can ask me anything and I will always be here:)");
        System.out.print(HLINE);
        System.out.println(" What can I do for you today?");
        System.out.print(HLINE);
    }
    //helper function
    private static void ensureTaskFormat(boolean cond, String message) throws TaskFormatException {
        if (!cond) throw new TaskFormatException(message+ '\n'+HLINE);
    }

    private static int ensureIndex(String input) throws TaskIndexException {
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
    public static void addTask(String input, Command command) throws TaskFormatException{
        System.out.print(HLINE);
        String description = input.substring(command.getLength()).trim();
        ensureTaskFormat(!description.isEmpty(), "The description of the task cannot be empty!");
        switch (command){
        case TODO:
            tasks.add(new Todo(description));
            break;
        case DEADLINE:
            description = input.substring(command.getLength(), input.indexOf('/') - 1).trim();
            String deadline = input.substring(input.indexOf("/by") + 3).trim();
            tasks.add( new Deadline(description, deadline));
            break;
        case EVENT:
            description = input.substring(command.getLength(), input.indexOf('/') - 1).trim();
            int indexOfFrom=input.indexOf("from");
            int indexOfTo=input.indexOf("to");
            String startTime = input.substring( indexOfFrom + 5, indexOfTo - 1).trim();
            String endTime = input.substring( indexOfTo + 3).trim();
            tasks.add(new Event(description, startTime, endTime));
            break;
        default:
            System.out.println("Unknown task type. Please use todo, deadline, or event.");
        }
        Task taskAdded = tasks.get(tasks.size() - 1);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + taskAdded.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
        System.out.println(HLINE);
    }

    public static void listTasks() {
        System.out.println(HLINE+"Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
        System.out.print(HLINE);
    }

    public static void markTask(String input) throws TaskIndexException{
        int index = ensureIndex(input);
        tasks.get(index - 1).mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index - 1).toString());
    }

    public static void unmarkTask(String input) throws TaskIndexException{
        int index = ensureIndex(input);
        tasks.get(index - 1).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index - 1).toString());
    }

    public static void run(Scanner in){
        while (true){
            String input = in.nextLine().trim();
            try {
                Command command = Command.fromInput(input.toLowerCase());
                if (command == null) {
                    throw new CommandException("Sorry but I don't know what " + input + " means. Pls start with todo/deadline/event/list/mark/unmark/bye.");
                }
                switch (command) {
                case LIST:
                    listTasks();
                    break;
                case BYE:
                    System.out.println(HLINE + "Bye. Hope to see you again soon!\n" + HLINE);
                    return;
                case UNMARK:
                    unmarkTask(input);
                    break;
                case MARK:
                    markTask(input);
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    addTask(input, command); // pass command into addTask
                    break;
                default:
                    throw new CommandException("Sorry but I don't know what " + input + " means. Pls start with todo/deadline/event/list/mark/unmark/bye.");
                }
                storage.save(tasks);
            } catch (CommandException e) {
                System.out.print(HLINE + e.getMessage()+'\n' + HLINE);
            } catch (TaskFormatException e) {
                System.out.println(e.getMessage());
            } catch (TaskIndexException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        tasks.addAll(storage.load());
        Scanner in = new Scanner(System.in);
        run(in);
    }
}