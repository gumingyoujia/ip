# Walkytalky User Guide

Welcome to **Walkytalky**, your personal chatbot that helps you manage tasks efficiently!

---

## Features

### 1. Add Tasks
Walkytalky supports three types of tasks:

- **Todo** `todo`– A simple task without a date/time.  
  **Command format:**
  ```
  todo <description>
  ```
  Example:
  ```
  todo Buy groceries
  ```

- **Deadline** `deadline`– A task with a due date/time.  
  **Command format:**
  ```
  deadline <description> /by <due date/time>
  ```
  Example:
  ```
  deadline Submit assignment /by 2025-10-05
  ```

- **Event** `event`– A task with a start and end time.  
  **Command format:**
  ```
  event <description> /from <start time> /to <end time>
  ```
  Example:
  ```
  event Team meeting /from 10:00 /to 12:00
  ```

---

### 2. List Tasks `list`
Display all tasks in your task list:

```
list
```

- Walkytalky shows all tasks with their index numbers and status.

---

### 3. Mark & Unmark Tasks 
- **Mark a task as done: `mark`**
  ```
  mark <task number>
  ```
- **Unmark a task as not done:`unmark`**
  ```
  unmark <task number>
  ```

Example:
```
mark 2
```
Marks the second task as completed.

---

### 4. Delete Tasks `delete`
Remove a task from your list:

```
delete <task number>
```

Example:
```
delete 3
```
Deletes the third task in the list.

---

### 5. Find Tasks `find`
Search for tasks containing a keyword:

```
find <keyword>
```

Example:
```
find meeting
```
Displays all tasks containing "meeting" in the description.

---

### 6. Exit Walkytalky `bye`
Close the chatbot gracefully:

```
bye
```

Walkytalky will show a goodbye message and save all tasks automatically.

---

## Tips

- Commands are **case-insensitive**, e.g., `TODO` and `todo` are treated the same.
- Always use the correct command format to avoid errors.
- Walkytalky automatically saves all your tasks, so you don’t have to worry about losing data.
