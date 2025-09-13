public enum Command {
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    MARK("mark"),
    UNMARK("unmark"),
    LIST("list"),
    DELETE("delete"),
    BYE("bye");

    private final String keyword;

    Command(String keyword) {
        this.keyword = keyword;
    }

    public String keyword() {
        return keyword;
    }

    public static Command fromInput(String input) {
        for (Command c : values()) {
            if (input.startsWith(c.keyword)) {
                return c;
            }
        }
        return null; // unknown command
    }

    public int getLength(){
        return keyword.length();
    }
}
