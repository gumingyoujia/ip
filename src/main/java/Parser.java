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

    public static int getIndex(String input, int tasksSize) throws TaskIndexException {
        String[] parts = input.split("\\s+", 2);
        if (parts.length < 2 || parts[1].isBlank()) {
            throw new TaskIndexException("Please provide a task number.");
        }
        try {
            int index = Integer.parseInt(parts[1].trim());
            if (index < 1 || index > tasksSize) {
                throw new TaskIndexException("Invalid task number: " + index);
            }
            return index;
        } catch (NumberFormatException e) {
            throw new TaskIndexException("Task number must be an integer.");
        }
    }
    public static String getKeyword(String input) throws TaskFormatException {
        String[] parts = input.split("\\s+", 2);
        if (parts.length < 2 || parts[1].isBlank()) {
            throw new TaskFormatException("Please provide a keyword.");
        }
        return parts[1].trim();
    }

}
