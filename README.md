# Hudou - Task Management CLI

Hudou is a simple Command-Line Interface (CLI) task manager that helps you keep track of your tasks efficiently. It allows users to add, delete, mark tasks as done, and save their task lists.

## Features
- **Add Tasks**: Supports different types of tasks (To-do, Deadline, Event).
- **Mark Tasks**: Mark tasks as completed or uncompleted.
- **Delete Tasks**: Remove tasks from your list.
- **List Tasks**: View all tasks in a structured format.
- **Save & Load Tasks**: Automatically saves tasks to a file and loads them when the program starts.
- **Find Tasks**: Search for specific tasks by keyword.

## Installation
### Prerequisites
- Java 11 or later installed on your system.

### Clone the Repository
```sh
$ git clone https://github.com/Hudou0420/ip.git
$ cd ip
```

## Running the Program
To compile and run the program:
```sh
$ javac -d bin src/main/java/Hudou/*.java
$ java -cp bin main.java.Hudou.Hudou
```

Alternatively, if you have a pre-built JAR file:
```sh
$ java -jar pathToJarFile
```

## Usage
### 1. Adding Tasks
- **To-do**: `todo <task_name>`
- **Deadline**: `deadline <task_name> /by <yyyy/MM/dd HH:mm>`
- **Event**: `event <task_name> /from <yyyy/MM/dd HH:mm> /to <yyyy/MM/dd HH:mm>`

### 2. Listing Tasks
```sh
list
```
Displays all tasks in order. For example:

- `1.[E][X] meeting  (from: 2025-02-27, 16:00, to: 2025-02-27, 18:00)`

- `2.[T][X] sleep`

- `3.[D][ ] eat  (by:2025-02-27, 22:00)`

-- You can find the task types, differentiated by `[T]`, `[D]` and `[E]`.

-- If the task is completed, the completion status will be `[X]`, otherwise it will be `[ ]`.

### 3. Marking Tasks
- **Mark as done**: `mark <task_number>`
- **Unmark**: `unmark <task_number>`

Alternatively, mark or unmark the task by its name is also possible. Make sure to type its full name!
- **Mark by name**: `mark <task_name>`
- **Unmark by name**: `unmark <task_name>`

### 4. Deleting Tasks
```sh
delete <task_number>
```
Removes the task from the list.

### 5. Finding Tasks
```sh
find <keyword>
```
Searches and lists tasks that contain the keyword.

### 6. Saving Tasks
```sh
save
```
Saves tasks to a file (`tasks.txt`).

- For Intellij Project, find it at `ProjectRoot/out/production`.
- For JAR file, it will be located at the same directory as the JAR file.

### 7. Exiting the Program
```sh
bye
```
Closes the program.

## File Storage
Hudou stores tasks in a file named `tasks.txt`. The file is automatically created if it does not exist.

## Error Handling
If you enter an invalid command, Hudou will notify you and provide guidance on the correct usage.

---

📢 **Stay productive with Hudou!** 🚀

