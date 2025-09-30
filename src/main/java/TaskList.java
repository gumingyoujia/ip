import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public List<Task> getAll() {
        return tasks;
    }

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

    public int size() {
        return tasks.size();
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
    }
}
