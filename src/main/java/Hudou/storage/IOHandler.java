package main.java.Hudou.storage;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import main.java.Hudou.exception.HudouException;
import main.java.Hudou.task.Task;
import main.java.Hudou.list.TaskList;
import static main.java.Hudou.exception.HudouException.JARFullNotifier;
import static main.java.Hudou.exception.HudouException.handleNoTaskNotifier;

/**
 * @file    IOHandler.java
 * @author  Hu Hongheng
 * @date    2025-02-27
 * @brief   Handles reading and writing tasks to a file for data persistence.
 *
 * This class is responsible for managing the storage of task data. It provides
 * methods to convert tasks to a savable format, write tasks to a file, and
 * read tasks from a stored file to initialize the application.
 */
public class IOHandler {

    private static final String storageFileName = "tasks.txt";

    /**
     * @brief Converts a list of Task objects to a String array for storage.
     *
     * This method is used to convert the tasks into a standard format where
     * attributes of different task types are separated by the "|" symbol.
     *
     * @param tasks The list of tasks to be converted.
     * @return An array of task strings, or null if the task list is null.
     */
    public static String[] convertTaskToString(ArrayList<Task> tasks){
        if (tasks == null){
            return null;
        }
        String[] tasksInString = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            tasksInString[i] = tasks.get(i).getTaskInString();
        }
        return tasksInString;
    }

    /**
     * @brief Writes the list of tasks to a file.
     *
     * This method initializes a file writer and saves tasks into a text file
     * in a structured format. If the file does not exist, it is created.
     *
     * @param tasks The list of tasks to be saved.
     */
    public static void writeTasksToFile(ArrayList<Task> tasks) {
        try {
            Path jarDir = FileUtils.getJarDirectory();
            if (jarDir == null) {
                JARFullNotifier();
                return;
            }

            Path filePath = jarDir.resolve(storageFileName);
            Files.createDirectories(filePath.getParent());

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
                String[] tasksInString = convertTaskToString(tasks);
                for (String task : tasksInString) {
                    if (task != null) {
                        writer.write(task);
                        writer.newLine();
                    }
                }
                System.out.println("Your tasks have been saved. Good work!");
            }
        } catch (IOException e) {
            System.err.println("Error writing tasks to file: " + e.getMessage());
        } catch (NullPointerException e) {
            handleNoTaskNotifier();
        } catch (Exception e) {
            System.err.println("Unexpected error while writing tasks: " + e.getMessage());
        }
    }

    /**
     * @brief Reads tasks from the storage file.
     *
     * This method initializes the chatbot by fetching task data from a text file.
     * If the file does not exist, it creates a new one.
     *
     * @return A TaskList object populated with tasks from the file.
     */
    public static TaskList readTasksFromFile() {
        List<String> taskList = new ArrayList<>();

        try {
            Path jarDir = FileUtils.getJarDirectory();
            if (jarDir == null) {
                JARFullNotifier();
                return new TaskList();
            }

            Path filePath = jarDir.resolve(storageFileName);
            File file = filePath.toFile();

            if (!file.exists()) {
                System.out.println(HudouException.fileNotFound);
                FileUtils.createFileInJarDirectory(storageFileName);
                return new TaskList();
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    taskList.add(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading tasks from file: " + e.getMessage());
        } catch (NullPointerException e) {
            handleNoTaskNotifier();
        } catch (Exception e) {
            System.err.println("Unexpected error while reading tasks: " + e.getMessage());
        }

        TaskList list = new TaskList();
        for (String task : taskList) {
            list.addTask(task, true);
        }
        return list;
    }
}
