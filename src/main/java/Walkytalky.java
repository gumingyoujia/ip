import java.util.Scanner;

public class Walkytalky {

    private static final int MAX_TASKS = 100;
    private static final String HLINE = "—".repeat(60)+'\n';
    private static final String LOGO =
            "▗▖ ▗▖ ▗▄▖ ▗▖   ▗▖ ▗▖▗▖  ▗▖▗▄▄▄▖▗▄▖ ▗▖   ▗▖ ▗▖▗▖  ▗▖\n" +
            "▐▌ ▐▌▐▌ ▐▌▐▌   ▐▌▗▞▘ ▝▚▞▘   █ ▐▌ ▐▌▐▌   ▐▌▗▞▘ ▝▚▞▘ \n" +
            "▐▌ ▐▌▐▛▀▜▌▐▌   ▐▛▚▖   ▐▌    █ ▐▛▀▜▌▐▌   ▐▛▚▖   ▐▌  \n" +
            "▐▙█▟▌▐▌ ▐▌▐▙▄▄▖▐▌ ▐▌  ▐▌    █ ▐▌ ▐▌▐▙▄▄▖▐▌ ▐▌  ▐▌  \n" +
            "                                                   ";

    private static Task[] tasks = new Task[MAX_TASKS];
    private static int count = 0;

    public static void printWelcomeMessage() {
        System.out.print(HLINE);
        System.out.println(" Hi!!! I am your\n" + LOGO);
        System.out.println(" You can ask me anything and I will always be here:)");
        System.out.print(HLINE);
        System.out.println(" What can I do for you today?");
        System.out.print(HLINE);
    }
    //helper function
    private static void require(boolean cond, String message) throws TaskFormatException {
        if (!cond) throw new TaskFormatException(message+ '\n'+HLINE);
    }

    private static void ensureCapacity() throws TaskCapacityException {
        if (count >= MAX_TASKS) {
            throw new TaskCapacityException(HLINE+ "Task list is full! (100 tasks max)"+ HLINE);
        }
    }

    public static void addTask(String input, Command command) throws TaskFormatException, TaskCapacityException{
        ensureCapacity();
        System.out.print(HLINE);
        String description = input.substring(command.getLength()).trim();
        require(!description.isEmpty(), "The description of the task cannot be empty!");
        switch (command){
        case TODO:
            tasks[count] = new Todo(description);
            break;
        case DEADLINE:
            description = input.substring(command.getLength(), input.indexOf('/') - 1).trim();
            String deadline = input.substring(input.indexOf("/by") + 3).trim();
            tasks[count] = new Deadline(description, deadline);
            break;
        case EVENT:
            int indexOfFrom=input.indexOf("from");
            int indexOfTo=input.indexOf("to");
            String startTime = input.substring( indexOfFrom + 5, indexOfTo - 1).trim();
            String endTime = input.substring( indexOfTo + 3).trim();
            tasks[count] = new Event(description, startTime, endTime);
            break;
        default:
            System.out.println("Unknown task type. Please use todo, deadline, or event.");
        }
        System.out.println("  " + tasks[count].toString());
        count++;
        System.out.println("Now you have " + count + " tasks in your list.");
        System.out.println(HLINE);
    }

    public static void listTasks() {
        System.out.println(HLINE+"Here are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + tasks[i].toString());
        }
        System.out.println("Now you have " + count + " tasks in your list.");
        System.out.print(HLINE);
    }

    private static boolean isValidIndex(int index) {
        return index >= 1 && index <= count;
    }

    public static void markTask(int index) {
        if (!isValidIndex(index)) {
            System.out.println("Invalid task number.");
            return;
        }
        tasks[index - 1].mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[index - 1].toString());
    }

    public static void unmarkTask(int index) {
        if (!isValidIndex(index)) {
            System.out.println("Invalid task number.");
            return;
        }
        tasks[index - 1].unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks[index - 1].toString());
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
                    unmarkTask(Integer.parseInt(input.split(" ")[1].trim()));
                    break;
                case MARK:
                    markTask(Integer.parseInt(input.split(" ")[1].trim()));
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    addTask(input, command); // pass command into addTask
                    break;
                default:
                    throw new CommandException("Sorry but I don't know what " + input + " means. Pls start with todo/deadline/event/list/mark/unmark/bye.");
                }
            } catch (CommandException e) {
                System.out.print(HLINE + e.getMessage()+'\n' + HLINE);
            } catch (TaskFormatException e) {
                System.out.println(e.getMessage());
            } catch (TaskCapacityException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        Scanner in = new Scanner(System.in);
        run(in);
    }
}