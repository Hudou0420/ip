package main.java.Hudou.storage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @file    FileUtils.java
 * @author  Hu Hongheng
 * @date    2025-02-27
 * @brief   Utility class for file operations in the Hudou application.
 *
 * This class provides utility methods for handling file-related operations,
 * such as retrieving the directory of the JAR file and creating files in
 * the JAR's directory.
 */
public class FileUtils {

    /**
     * @brief Retrieves the directory where the JAR file is located.
     *
     * This method returns the parent directory of the JAR file where the
     * application is running. If an error occurs, it prints an error message
     * and returns null.
     *
     * @return The Path representing the JAR's directory or null if an error occurs.
     * @throws URISyntaxException If the URI syntax is incorrect.
     */
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

    /**
     * @brief Creates a file in the JAR's directory.
     *
     * This method attempts to create a file with the given name inside
     * the JAR's directory. If the file already exists, it notifies the user.
     *
     * @param fileName The name of the file to be created.
     */
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
