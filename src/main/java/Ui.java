import java.util.Scanner;

public class Ui {
    private static final String HLINE = "—".repeat(60) + '\n';
    private static final String LOGO =
            "▗▖ ▗▖ ▗▄▖ ▗▖   ▗▖ ▗▖▗▖  ▗▖▗▄▄▄▖▗▄▖ ▗▖   ▗▖ ▗▖▗▖  ▗▖\n" +
            "▐▌ ▐▌▐▌ ▐▌▐▌   ▐▌▗▞▘ ▝▚▞▘   █ ▐▌ ▐▌▐▌   ▐▌▗▞▘ ▝▚▞▘ \n" +
            "▐▌ ▐▌▐▛▀▜▌▐▌   ▐▛▚▖   ▐▌    █ ▐▛▀▜▌▐▌   ▐▛▚▖   ▐▌  \n" +
            "▐▙█▟▌▐▌ ▐▌▐▙▄▄▖▐▌ ▐▌  ▐▌    █ ▐▌ ▐▌▐▙▄▄▖▐▌ ▐▌  ▐▌  \n" +
            "                                                   ";

    private final Scanner in;

    public Ui() {
        this.in = new Scanner(System.in);
    }

    public void printWelcomeMessage() {
        System.out.print(HLINE);
        System.out.println(" Hi!!! I am your\n" + LOGO);
        System.out.println(" You can ask me anything and I will always be here:)");
        System.out.print(HLINE);
        System.out.println(" What can I do for you today?");
        System.out.print(HLINE);
    }

    public void showLine() {
        System.out.print(HLINE);
    }

    public void showErrorMessage(String message) {
        System.out.println(message);
        showLine();
    }

    public String readCommand() {
        return in.nextLine().trim();
    }

    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }


    public void showMarkMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    public void showUnmarkMessage(Task task) {
        System.out.println("Nice! I've unmarked this task:");
        System.out.println(task.toString());
    }

    public void showDeleteMessage(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("   " + task);
    }
}
