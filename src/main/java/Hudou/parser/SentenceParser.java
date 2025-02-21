package main.java.Hudou.parser;

public class SentenceParser {

    /**
     * @brief method to get the substring of the input, from second word onwards
     * the purpose of this method is to separate command type and the actual task name
     * and other types of commands
     * @param str input argument from the command line
     * @return String of the input after the first word. Usually the task name and
     * its required parameters
     */
    public static String getSubstringFromSecondWord(String str) {
        String[] words = str.split(" "); // Split by spaces
        return words.length > 1 ? String.join(" ",
                java.util.Arrays.copyOfRange(words, 1, words.length)) : "";
    }

    /**
     * @brief this method splits the user's input for special task type
     * such as "Events". Its purpose is to identify 2 arguments from the
     * user input, such as "/from" and "/to", to store be called in the
     * constructor and store it in the child class inherited from this
     * parent class.
     * @param str input argument from the command line
     * @param keyword the word to be found in the string and
     *                split the string to front and back substring
     * @return an String array size of 2, containing front and back of the
     *         complete string, excluding the keyword
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
