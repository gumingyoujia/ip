public class Parser {

    public static Command parseCommand(String input) throws CommandException {
        Command command = Command.fromInput(input.toLowerCase());
        if (command == null) {
            throw new CommandException("Sorry but I don't know what " + input +
                    " means. Pls start with todo/deadline/event/list/mark/unmark/bye.");
        }
        return command;
    }

    public static Task parseTask(String input, Command command) throws TaskFormatException {
        switch (command) {
        case TODO:
            String description = input.substring(command.getLength()).trim();
            if (description.isEmpty()) {
                throw new TaskFormatException("The description of a todo cannot be empty!");
            }
            return new Todo(description);

        case DEADLINE:
            String descD = input.substring(command.getLength(), input.indexOf('/') - 1).trim();
            String deadline = input.substring(input.indexOf("/by") + 3).trim();
            return new Deadline(descD, deadline);

        case EVENT:
            String descE = input.substring(command.getLength(), input.indexOf('/') - 1).trim();
            int indexOfFrom = input.indexOf("from");
            int indexOfTo = input.indexOf("to");
            String startTime = input.substring(indexOfFrom + 5, indexOfTo - 1).trim();
            String endTime = input.substring(indexOfTo + 3).trim();
            return new Event(descE, startTime, endTime);

        default:
            throw new TaskFormatException("Unknown task type. Please use todo, deadline, or event.");
        }
    }
}
