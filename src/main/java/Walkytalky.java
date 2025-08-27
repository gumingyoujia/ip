import java.util.Scanner;
public class Walkytalky {

    private static String[] tasks = new String[100];
    private static int count = 0;

    public static void printWelcomeMessage() {
        System.out.println("—".repeat(60));
        String logo = "▗▖ ▗▖ ▗▄▖ ▗▖   ▗▖ ▗▖▗▖  ▗▖▗▄▄▄▖▗▄▖ ▗▖   ▗▖ ▗▖▗▖  ▗▖\n" +
                "▐▌ ▐▌▐▌ ▐▌▐▌   ▐▌▗▞▘ ▝▚▞▘   █ ▐▌ ▐▌▐▌   ▐▌▗▞▘ ▝▚▞▘ \n" +
                "▐▌ ▐▌▐▛▀▜▌▐▌   ▐▛▚▖   ▐▌    █ ▐▛▀▜▌▐▌   ▐▛▚▖   ▐▌  \n" +
                "▐▙█▟▌▐▌ ▐▌▐▙▄▄▖▐▌ ▐▌  ▐▌    █ ▐▌ ▐▌▐▙▄▄▖▐▌ ▐▌  ▐▌  \n" +
                "                                                   ";
        System.out.println(" Hi!!! I am your\n" + logo);
        System.out.println(" You can ask me anything and I will always be here:)");
        System.out.println("—".repeat(60));
        System.out.println(" What can I do for you today?");
        System.out.println("—".repeat(60));
    }
    public static void echo(Scanner in){
        while (true){
            String input = in.nextLine();
            if (input.equalsIgnoreCase("bye")){
                System.out.println("—".repeat(60)+"\nBye. Hope to see you again soon!\n"+"—".repeat(60));
                return;
            }
            System.out.println("—".repeat(60)+"\n"+input+"\n"+"—".repeat(60));
        }
    }
    public static void addTask(String task){
        if (count < tasks.length) {
            tasks[count] = task;
            count++;
            System.out.println("—".repeat(60));
            System.out.println("added: " + task);
            System.out.println("—".repeat(60));
        } else {
            System.out.println("Task list is full! (100 tasks max)");
        }
    }

    public static void listTasks() {
        System.out.println("—".repeat(60));
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println("—".repeat(60));
    }

    public static void addAndList(Scanner in){
        while (true){
            String input = in.nextLine();
            if (input.equalsIgnoreCase("list")){
                listTasks();
            }
            else if (input.equalsIgnoreCase("bye")){
                System.out.println("—".repeat(60)+"\nBye. Hope to see you again soon!\n"+"—".repeat(60));
                return;
            }
            else {
                addTask(input);
            }
        }

    }

    public static void main(String[] args) {
        printWelcomeMessage();
        Scanner in = new Scanner(System.in);
        addAndList(in);
    }
}