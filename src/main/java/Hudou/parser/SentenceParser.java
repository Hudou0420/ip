package main.java.Hudou.parser;

/**
 * @file    SentenceParser.java
 * @author  Hu Hongheng
 * @date    2025-02-27
 * @brief   Provides utility methods to process and parse user input sentences.
 *
 * This class contains methods to extract parts of a user input string,
 * such as separating the task name from the command type and handling
 * special keyword-based task parsing.
 */
public class SentenceParser {

    /**
     * @brief Extracts the substring from the second word onwards in an input string.
     *
     * This method is used to separate the command type from the actual task name
     * or command arguments. It returns everything after the first word in the input.
     *
     * @param str The user input string.
     * @return The substring starting from the second word, or an empty string if no second word exists.
     */
    public static String getSubstringFromSecondWord(String str) {
        String[] words = str.split(" "); // Split by spaces
        return words.length > 1 ? String.join(" ",
                java.util.Arrays.copyOfRange(words, 1, words.length)) : "";
    }

    /**
     * @brief Splits the user's input string based on a given keyword.
     *
     * This method is specifically used for tasks like "Events" that require
     * multiple arguments (e.g., "/from" and "/to"). It separates the string
     * into two parts: the portion before the keyword and the portion after.
     *
     * @param str The user input string.
     * @param keyword The keyword used to split the string.
     * @return A String array of size 2, where:
     *         - Index 0 contains the substring before the keyword.
     *         - Index 1 contains the substring after the keyword.
     *         If the keyword is not found, the entire string is placed in index 0, and index 1 is empty.
     */
    public static String[] splitBySubstringCommands(String str, String keyword) {
        int index = str.indexOf(keyword);
        if (index == -1) {
            return new String[]{str, ""};  // If not found, return the full string as front, empty back
        }
        String front = str.substring(0, index);
        String back = str.substring(index + keyword.length());
        return new String[]{front, back};
    }
}
