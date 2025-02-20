package main.java.Hudou.storage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    //method to get the directory where the JAR file is located
    public static Path getJarDirectory() throws URISyntaxException {
        try {
            Path path = Paths.get(new File(FileUtils.class.getProtectionDomain()
                    .getCodeSource().getLocation().toURI()).getParent());
            return path;
        } catch (NullPointerException e){
            System.err.println("Unexpected null reference while retrieving JAR directory.");
            return null;
        }
    }

    //method to create a file in the JAR's directory
    public static void createFileInJarDirectory(String fileName) {
        try {
            Path jarDir = getJarDirectory();
            Path filePath = jarDir.resolve(fileName);

            Files.createDirectories(filePath.getParent());
            File file = filePath.toFile();

            if (file.createNewFile()) {
                System.out.println("File created: " + file.getAbsolutePath());
            } else {
                System.out.println("File already exists: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("File operation failed for: " + fileName + " - " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Unexpected null reference while handling file: " + fileName);
        } catch (URISyntaxException e) {
            System.err.println("Invalid URI while retrieving JAR directory.");
        }
    }
}
