import java.util.Scanner;
public class Walkytalky {
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

    public static void main(String[] args) {
        printWelcomeMessage();
        Scanner in = new Scanner(System.in);
        echo(in);
    }
}