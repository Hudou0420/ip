package main.java.Hudou.command;

import main.java.Hudou.task.TaskList;
import main.java.Hudou.exception.HudouException;
import main.java.Hudou.parser.SentenceParser;

public class MarkCommand extends Command {
    private String input;
    private boolean isDone;

    public MarkCommand(String input, boolean isDone) {
        this.input = input;
        this.isDone = isDone;
    }

    @Override
    //method to call when user marks or unmarks command
    //the method can handle both search by index and search by name cases
    public void execute(TaskList taskList) {
        String[] inputs = input.split(" ");
        try {
            if (ChatBot.isInteger(inputs[1])) {
                if (isDone) {
                    taskList.markDone(Integer.parseInt(inputs[1]));
                } else {
                    taskList.markUndone(Integer.parseInt(inputs[1]));
                }
            } else {
                String taskName = SentenceParser.getSubstringFromSecondWord(input);
                if (isDone) {
                    taskList.markDone(taskName);
                } else {
                    taskList.markUndone(taskName);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            HudouException.handleChatBotInvalidInput();
        }
    }
}
