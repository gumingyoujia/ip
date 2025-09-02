import java.util.Scanner;

public class Walkytalky {

    private static final int MAX_TASKS = 100;
    private static final String HLINE = "—".repeat(60);
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String LOGO =
            "▗▖ ▗▖ ▗▄▖ ▗▖   ▗▖ ▗▖▗▖  ▗▖▗▄▄▄▖▗▄▖ ▗▖   ▗▖ ▗▖▗▖  ▗▖\n" +
            "▐▌ ▐▌▐▌ ▐▌▐▌   ▐▌▗▞▘ ▝▚▞▘   █ ▐▌ ▐▌▐▌   ▐▌▗▞▘ ▝▚▞▘ \n" +
            "▐▌ ▐▌▐▛▀▜▌▐▌   ▐▛▚▖   ▐▌    █ ▐▛▀▜▌▐▌   ▐▛▚▖   ▐▌  \n" +
            "▐▙█▟▌▐▌ ▐▌▐▙▄▄▖▐▌ ▐▌  ▐▌    █ ▐▌ ▐▌▐▙▄▄▖▐▌ ▐▌  ▐▌  \n" +
            "                                                   ";

    private static Task[] tasks = new Task[MAX_TASKS];
    private static int count = 0;

    public static void printWelcomeMessage() {
        System.out.println(HLINE);

        System.out.println(" Hi!!! I am your\n" + LOGO);
        System.out.println(" You can ask me anything and I will always be here:)");
        System.out.println(HLINE);
        System.out.println(" What can I do for you today?");
        System.out.println(HLINE);
    }

    public static void addTask(String input){
        if (count < tasks.length) {
            System.out.println(HLINE);
            if (input.startsWith(COMMAND_TODO)){
                String description= input.substring(COMMAND_TODO.length()).trim();
                tasks[count] = new Todo(description);
            } else if (input.startsWith(COMMAND_DEADLINE)){
                String deadline = input.substring(input.indexOf("/by") + 3).trim();
                String description = input.substring(0,input.indexOf('/') - 1).trim();
                tasks[count] = new Deadline(description, deadline);
            } else if (input.startsWith(COMMAND_EVENT)){
                int indexOfFrom=input.indexOf("from");
                int indexOfTo=input.indexOf("to");
                String description = input.substring(0, input.indexOf('/') - 1).trim();
                String startTime = input.substring( indexOfFrom + 5, indexOfTo - 1).trim();
                String endTime = input.substring( indexOfTo + 3).trim();
                tasks[count] = new Event(description, startTime, endTime);
            } else {
            System.out.println("Unknown task type. Please use todo, deadline, or event.");
            return;
            }
            System.out.println("  " + tasks[count].toString());
            count++;
            System.out.println("Now you have " + count + " tasks in your list.");
            System.out.println(HLINE);
        } else {
            System.out.println("Task list is full! (100 tasks max)");
        }
    }

    public static void listTasks() {
        System.out.println(HLINE+"\nHere are the tasks in your list:");
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + tasks[i].toString());
        }
        System.out.println("Now you have " + count + " tasks in your list.");
        System.out.println(HLINE);
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
            if (input.equalsIgnoreCase(COMMAND_LIST)){
                listTasks();
            } else if (input.equalsIgnoreCase(COMMAND_BYE)){
                System.out.println(HLINE+"\nBye. Hope to see you again soon!\n"+HLINE);
                return;
            } else if (input.startsWith(COMMAND_UNMARK)){
                unmarkTask(Integer.parseInt(input.split(" ")[1].trim()));
            } else if (input.startsWith(COMMAND_MARK)){
                markTask(Integer.parseInt(input.split(" ")[1].trim()));
            } else {
                addTask(input);
            }
        }
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        Scanner in = new Scanner(System.in);
        run(in);
    }
}