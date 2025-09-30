import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks and provides operations to manage them,
 * such as adding, deleting, searching, and listing tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with existing tasks.
     *
     * @param tasks Initial list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Returns the internal task list.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task to the task list and displays a confirmation message.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index Zero-based index of the task to delete.
     * @return The removed task.
     */
    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Retrieves the task at the specified index.
     *
     * @param index Zero-based index of the task.
     * @return The task at the index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns all tasks in the list.
     *
     * @return List of all tasks.
     */
    public List<Task> getAll() {
        return tasks;
    }

    /**
     * Searches for tasks containing the given text in their description.
     *
     * @param searchText Keyword to search for.
     */
    public void searchTask(String searchText) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(searchText)) {
                result.add(task);
            }
        }
        if (result.isEmpty()) {
            System.out.println("Sorry, I can't find such task.");
            return;
        }
        for (int i = 0; i < result.size(); i++) {
            System.out.println((i + 1) + ". " + result.get(i));
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Task count.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Prints all tasks with their indices and total count.
     */
    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
    }
}
