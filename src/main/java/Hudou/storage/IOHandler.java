package main.java.Hudou.storage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import main.java.Hudou.exception.HudouException;
import main.java.Hudou.task.Task;
import main.java.Hudou.task.TaskList;
import static main.java.Hudou.exception.HudouException.JARFullNotifier;
import static main.java.Hudou.exception.HudouException.handleNoTaskNotifier;

//this class is mainly used to handle the storage format and to write, create, modify
//the data in the txt file.
public class IOHandler {

    private static final String storageFileName = "tasks.txt";

    //this method is called when saving tasks into the txt file, to parse into a standard format
    //each attribute in different task types will be stored separated by "|" symbol
    //because each task type has different attributes, look into the taks classes for more detail
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

    //this method is called to initialise file writer
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
                System.out.println("Your tasks has been saved. Good work!");
            }
        } catch (IOException e) {
            System.err.println("Error writing tasks to file: " + e.getMessage());
        } catch (NullPointerException e) {
            handleNoTaskNotifier();
        } catch (Exception e) {
            System.err.println("Unexpected error while writing tasks: " + e.getMessage());
        }
    }

    //this method is called when the chatbot is initialising, it will fetch the content
    //inside the txt file.
    //the path of the txt file is hardcoded to be in the same directory as the project.
    //if using Intellij IDEA, then it will be in yourProjectRoot/out/production/"task.txt"
    //if using JAR, then it will be in the same directory as the JAR file
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
        }
        catch (Exception e) {
            System.err.println("Unexpected error while reading tasks: " + e.getMessage());
        }

        TaskList list = new TaskList();
        for (String task : taskList) {
            list.addTask(task, true);
        }
        return list;
    }


}
