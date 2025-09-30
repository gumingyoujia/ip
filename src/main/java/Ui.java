import java.util.Scanner;

/**
 * Handles all user interaction for the Walkytalky application.
 * Provides methods to display messages, read user commands,
 * and show feedback for task operations.
 */
public class Ui {
    private static final String HLINE = "—".repeat(60) + '\n';
    private static final String LOGO =
            "▗▖ ▗▖ ▗▄▖ ▗▖   ▗▖ ▗▖▗▖  ▗▖▗▄▄▄▖▗▄▖ ▗▖   ▗▖ ▗▖▗▖  ▗▖\n" +
            "▐▌ ▐▌▐▌ ▐▌▐▌   ▐▌▗▞▘ ▝▚▞▘   █ ▐▌ ▐▌▐▌   ▐▌▗▞▘ ▝▚▞▘ \n" +
            "▐▌ ▐▌▐▛▀▜▌▐▌   ▐▛▚▖   ▐▌    █ ▐▛▀▜▌▐▌   ▐▛▚▖   ▐▌  \n" +
            "▐▙█▟▌▐▌ ▐▌▐▙▄▄▖▐▌ ▐▌  ▐▌    █ ▐▌ ▐▌▐▙▄▄▖▐▌ ▐▌  ▐▌  \n" +
            "                                                   ";

    private final Scanner in;

    /**
     * Constructs a new Ui object that reads input from standard input.
     */
    public Ui() {
        this.in = new Scanner(System.in);
    }

    /**
     * Prints the welcome message and application logo to the user.
     */
    public void printWelcomeMessage() {
        System.out.print(HLINE);
        System.out.println(" Hi!!! I am your\n" + LOGO);
        System.out.println(" You can ask me anything and I will always be here:)");
        System.out.print(HLINE);
        System.out.println(" What can I do for you today?");
        System.out.print(HLINE);
    }

    /**
     * Prints a horizontal line divider.
     */
    public void showLine() {
        System.out.print(HLINE);
    }

    /**
     * Displays an error message to the user, followed by a line divider.
     *
     * @param message Error message to display.
     */
    public void showErrorMessage(String message) {
        System.out.println(message);
        showLine();
    }

    /**
     * Reads a command entered by the user.
     *
     * @return Trimmed input string entered by the user.
     */
    public String readCommand() {
        return in.nextLine().trim();
    }

    /**
     * Prints the exit message when the user chooses to quit.
     */
    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    /**
     * Displays a confirmation message after marking a task as done.
     *
     * @param task Task that was marked as done.
     */
    public void showMarkMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    /**
     * Displays a confirmation message after unmarking a task.
     *
     * @param task Task that was unmarked.
     */
    public void showUnmarkMessage(Task task) {
        System.out.println("Nice! I've unmarked this task:");
        System.out.println(task.toString());
    }

    /**
     * Displays a confirmation message after deleting a task.
     *
     * @param task Task that was deleted.
     */
    public void showDeleteMessage(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("   " + task);
    }
}
