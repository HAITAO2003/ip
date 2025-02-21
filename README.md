# Duke Task Manager - User Guide
<img width="399" alt="image" src="https://github.com/user-attachments/assets/8837ab33-5696-4e45-9bbc-9fcdebd1711b" />


Duke is a desktop chat-based task manager application that helps you keep track of your tasks, deadlines, and events through a friendly chat interface.

## Table of Contents
- [Quick Start](#quick-start)
- [Features](#features)
  - [Adding a Todo Task](#adding-a-todo-task)
  - [Adding a Deadline Task](#adding-a-deadline-task)
  - [Adding an Event](#adding-an-event)
  - [Listing All Tasks](#listing-all-tasks)
  - [Marking a Task as Done](#marking-a-task-as-done)
  - [Deleting a Task](#deleting-a-task)
  - [Finding Tasks](#finding-tasks)
  - [Exiting the Program](#exiting-the-program)
- [Command Summary](#command-summary)
- [Data Storage](#data-storage)
- [FAQ](#faq)

## Quick Start

1. Ensure you have Java 11 or above installed on your computer
2. Download the latest version of Duke from [here](link-to-release)
3. Run the JAR file using: `java -jar duke.jar`
4. The GUI should appear with a welcome message

## Features

Duke uses a command-line interface within a chat window. All task information is automatically saved after every command.

### Adding a Todo Task

Add a simple task without any specific deadline.

Format: `todo DESCRIPTION`

Example:
```
todo read book
```

Response:
```
Got it. I've added this task:
     [T][ ] read book
Now you have 1 tasks in the list.
```

### Adding a Deadline Task

Add a task with a specific deadline.

Format: `deadline DESCRIPTION /by DATE_TIME`

> Note: The date format must be: d/M/yyyy HHmm (e.g., 2/12/2023 1800)

Example:
```
deadline complete assignment /by 5/10/2023 2359
```

Response:
```
Got it. I've added this task:
     [D][ ] complete assignment (by: Oct 5 2023, 11:59PM)
Now you have 2 tasks in the list.
```

### Adding an Event

Add a task that occurs during a specific time period.

Format: `event DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME`

> Note: Both date times must be in format: d/M/yyyy HHmm (e.g., 2/12/2023 1800)

Example:
```
event team meeting /from 10/10/2023 1400 /to 10/10/2023 1600
```

Response:
```
Got it. I've added this task:
     [E][ ] team meeting (from: Oct 10 2023, 2:00PM to: Oct 10 2023, 4:00PM)
Now you have 3 tasks in the list.
```

### Listing All Tasks

Show all tasks currently stored in Duke.

Format: `list`

Example:
```
list
```

Response:
```
Here are the tasks in your list:
1.     [T][ ] read book
2.     [D][ ] complete assignment (by: Oct 5 2023, 11:59PM)
3.     [E][ ] team meeting (from: Oct 10 2023, 2:00PM to: Oct 10 2023, 4:00PM)
```

### Marking a Task as Done

Mark a specific task as completed.

Format: `done INDEX`

Example:
```
done 1
```

Response:
```
Nice! I've marked this task as done:
     [T][X] read book
```

### Deleting a Task

Remove a task from your list.

Format: `delete INDEX`

Example:
```
delete 2
```

Response:
```
Noted. I've removed this task:
     [D][ ] complete assignment (by: Oct 5 2023, 11:59PM)
Now you have 2 tasks in the list.
```

### Finding Tasks

Find tasks that contain a specific keyword in their descriptions.

Format: `find KEYWORD`

Example:
```
find meeting
```

Response:
```
Here are the matching tasks in your list:
1.     [E][ ] team meeting (from: Oct 10 2023, 2:00PM to: Oct 10 2023, 4:00PM)
```

### Exiting the Program

Exit the Duke application.

Format: `bye`

Example:
```
bye
```

Response:
```
Bye. Hope to see you again soon!
```

## Command Summary

| Command | Format | Example |
|---------|--------|---------|
| **Todo** | `todo DESCRIPTION` | `todo read book` |
| **Deadline** | `deadline DESCRIPTION /by DATE_TIME` | `deadline complete assignment /by 5/10/2023 2359` |
| **Event** | `event DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME` | `event team meeting /from 10/10/2023 1400 /to 10/10/2023 1600` |
| **List** | `list` | `list` |
| **Mark as Done** | `done INDEX` | `done 1` |
| **Delete** | `delete INDEX` | `delete 2` |
| **Find** | `find KEYWORD` | `find meeting` |
| **Exit** | `bye` | `bye` |

## Data Storage

Duke automatically saves your tasks to a file in the `data` directory. The file is named `tasks.txt` and is created automatically if it doesn't exist.

## FAQ

**Q: What happens if I enter an invalid command?**  
A: Duke will show an error message explaining what went wrong.

**Q: Can I edit a task after adding it?**  
A: Currently, you need to delete the task and add it again with the corrected information.

**Q: What happens if I enter an invalid date format?**  
A: Duke will show an error message reminding you to use the format: d/M/yyyy HHmm (e.g., 2/12/2023 1800).

**Q: Will I lose my data if the application crashes?**  
A: Duke saves your tasks after every command, so you will only lose changes from the current command if the application crashes.

---

