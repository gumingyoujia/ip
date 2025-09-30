import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * Handles loading tasks from disk and saving tasks to disk.
 * Tasks are stored in a plain text format and reconstructed at runtime.
 */
public class Storage {
    private final File file;
    private final String HLINE = "—".repeat(60)+'\n';

    /**
     * Constructs a Storage object for the given file path.
     *
     * @param filePath Path to the task file.
     */
    public Storage(String filePath) {
        file = new File(filePath);
    }

    private List<Task> loadFileContents() throws FileNotFoundException {
        ensureParents();
        List<Task> list = new ArrayList<>();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isBlank()) {
                continue;
            }
            Task t = parse(line);
            if (t != null) {
                list.add(t);
            }
        }
        return list;
    }

    /**
     * Loads tasks from disk. If no file exists, an empty list is returned.
     *
     * @return List of loaded tasks.
     */
    public List<Task> load() {
        List<Task> list = new ArrayList<>();
        try {
            list=loadFileContents();
        } catch (FileNotFoundException e) {
            System.out.println(" There is no existing task list.\n" + HLINE);
            return list;
        }
        return list;
    }

    /**
     * Saves all tasks to disk, overwriting the file.
     *
     * @param tasks List of tasks to save.
     */
    public void save(List<Task> tasks) {
        ensureParents();
        try (FileWriter fw = new FileWriter(file)) {
            for (Task t : tasks) {
                fw.write(format(t));
                fw.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Warning: failed to save tasks to disk.");
        }
    }

    private void ensureParents() {
        File parent = file.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }
    }

    private Task parse(String line) {
        String[] p = line.split("\\|");
        for (int i = 0; i < p.length; i++) p[i] = p[i].trim();
        if (p.length < 3) return null;

        String type = p[0];
        boolean done = "1".equals(p[1]);
        String desc = p[2];

        switch (type) {
        case "T": {
            Task t = new Todo(desc);
            if (done) t.mark(); else t.unmark();
            return t;
        }
        case "D": {
            if (p.length < 4) return null;
            Task t = new Deadline(desc, p[3]);
            if (done) t.mark(); else t.unmark();
            return t;
        }
        case "E": {
            if (p.length < 4) return null;
            String when = p[3];
            int to = when.toLowerCase().indexOf(" to ");
            String start = to > 0 ? when.substring(0, to).trim() : when;
            String end   = to > 0 ? when.substring(to + 4).trim() : "";
            Task t = new Event(desc, start, end);
            if (done) t.mark(); else t.unmark();
            return t;
        }
        default: return null;
        }
    }

    private String format(Task t) {
        String done = t.isDone()? "1" : "0";
        if (t instanceof Todo) {
            return String.format("T | %s | %s", done, t.getDescription());
        } else if (t instanceof Deadline) {
            Deadline d = (Deadline) t;
            return String.format("D | %s | %s | %s", done, d.getDescription(), d.getBy());
        } else if (t instanceof Event) {
            Event e = (Event) t;
            String when = (e.getEndTime() == null || e.getEndTime().isBlank())
                    ? e.getStartTime() : e.getStartTime() + " to " + e.getEndTime();
            return String.format("E | %s | %s | %s", done, e.getDescription(), when);
        }
        return String.format("T | %s | %s", done, t.getDescription()); // fallback
    }
}

