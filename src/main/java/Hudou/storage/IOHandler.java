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

public class IOHandler {

    private static final String storageFileName = "tasks.txt";

    public static String[] convertTaskToString(ArrayList<Task> tasks){
        if (tasks == null){
            return null;             //add handle exceptions later
        }
        String[] tasksInString = new String[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            tasksInString[i] = tasks.get(i).getTaskInString();
        }
        return tasksInString;
    }

    public static String[] convertTaskToString(Task[] tasks){
        if (tasks == null){
            return null;             //add handle exceptions later
        }
        String[] tasksInString = new String[tasks.length];
        int i = 0;
        while (i < tasks.length && tasks[i] != null){
            tasksInString[i] = tasks[i].getTaskInString();
            i++;
        }
//        for (int i = 0; i < tasks.length; i++) {
//            if (tasks[i] != null) {
//                tasksInString[i] = tasks[i].getTaskInString();
//            }
//        }
        return tasksInString;
    }

    public static void writeTasksToFile(String fileName, ArrayList<Task> tasks){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            String[] tasksInString = convertTaskToString(tasks);
            for (String task : tasksInString) {
                writer.write(task);
                writer.newLine();
            }
            System.out.println("Tasks written to file successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
