/**
 * Parses user input into commands, tasks, indices, and keywords.
 * Provides utility methods to handle string processing for task management.
 */
public class Parser {

    /**
     * Parses the user input into a Command.
     *
     * @param input User input string.
     * @return Corresponding Command.
     * @throws CommandException If the input does not match any command.
     */
    public static Command parseCommand(String input) throws CommandException {
        Command command = Command.fromInput(input.toLowerCase());
        if (command == null) {
            throw new CommandException("Sorry but I don't know what " + input +
                    " means. Pls start with todo/deadline/event/list/mark/unmark/bye.");
        }
        return command;
    }

    /**
     * Parses the user input into a specific Task, based on the command.
     *
     * @param input   User input string.
     * @param command Command indicating the type of task.
     * @return A Task object corresponding to the command.
     * @throws TaskFormatException If the input format is invalid for the given command.
     */
    public static Task parseTask(String input, Command command) throws TaskFormatException {
        switch (command) {
        case TODO:
            String description = input.substring(command.getLength()).trim();
            if (description.isEmpty()) {
                throw new TaskFormatException("The description cannot be empty!");
            }
            return new Todo(description);

        case DEADLINE:
            int byIndex = input.indexOf("/by");
            if (byIndex == -1) {
                throw new TaskFormatException("Deadline must include '/by' followed by a date/time.");
            }

            String descD = input.substring(command.getLength(), byIndex).trim();
            if (descD.isEmpty()) {
                throw new TaskFormatException("The description of a deadline cannot be empty!");
            }

            String deadline = input.substring(byIndex + 3).trim();
            if (deadline.isEmpty()) {
                throw new TaskFormatException("The deadline date/time cannot be empty!");
            }

            return new Deadline(descD, deadline);

        case EVENT:
            int indexOfFrom = input.indexOf("/from");
            int indexOfTo = input.indexOf("/to");

            if (indexOfFrom == -1 || indexOfTo == -1 || indexOfFrom > indexOfTo) {
                throw new TaskFormatException(
                        "Event must be in format: event <description> /from <start> /to <end>"
                );
            }

            // Extract description
            String descE = input.substring(command.getLength(), indexOfFrom).trim();
            if (descE.isEmpty()) {
                throw new TaskFormatException("The description of an event cannot be empty!");
            }

            // Extract start and end times
            String startTime = input.substring(indexOfFrom + 4, indexOfTo).trim(); // +4 to skip "from"
            String endTime = input.substring(indexOfTo + 2).trim();                // +2 to skip "to"

            if (startTime.isEmpty() || endTime.isEmpty()) {
                throw new TaskFormatException("Start time and end time must be provided for an event.");
            }

            return new Event(descE, startTime, endTime);


        default:
            throw new TaskFormatException("Unknown task type. Please use todo, deadline, or event.");
        }
    }

    /**
     * Extracts and validates the task index from user input.
     *
     * @param input     User input string.
     * @param tasksSize Current number of tasks.
     * @return Task index (1-based).
     * @throws TaskIndexException If index is missing, invalid, or out of bounds.
     */
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

    /**
     * Extracts the keyword from user input for search operations.
     *
     * @param input User input string.
     * @return Search keyword.
     * @throws TaskFormatException If no keyword is provided.
     */
    public static String getKeyword(String input) throws TaskFormatException {
        String[] parts = input.split("\\s+", 2);
        if (parts.length < 2 || parts[1].isBlank()) {
            throw new TaskFormatException("Please provide a keyword.");
        }
        return parts[1].trim();
    }

}
