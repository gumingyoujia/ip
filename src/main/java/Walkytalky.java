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
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("—".repeat(60));
    }
    public static void main(String[] args) {
        printWelcomeMessage();
    }
}
