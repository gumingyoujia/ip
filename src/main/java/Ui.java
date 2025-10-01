import java.util.Scanner;

/**
 * Handles all user interaction for the Walkytalky application.
 * Provides methods to display messages, read user commands,
 * and show feedback for task operations.
 */
public class Ui {
    private static final String HLINE = "_".repeat(60) + '\n';
    private static final String LOGO = """
                           _ _          _        _ _         \s
                          | | |        | |      | | |        \s
            __      ____ _| | | ___   _| |_ __ _| | | ___   _\s
            \\ \\ /\\ / / _` | | |/ / | | | __/ _` | | |/ / | | |
             \\ V  V / (_| | |   <| |_| | || (_| | |   <| |_| |
              \\_/\\_/ \\__,_|_|_|\\_\\\\__, |\\__\\__,_|_|_|\\_\\\\__, |
                                   __/ |                 __/ |
                                  |___/                 |___/\s
        """;

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
        System.out.println("Hi!!! I am \n" + LOGO);
        System.out.println("your personal chatbot that helps you manage tasks efficiently!");
        System.out.print(HLINE);
        System.out.println("What do you want to add to your task list today?");
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
        System.out.println("Bye. Hope to see you again soon!");
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
