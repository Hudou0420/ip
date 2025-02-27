package main.java.Hudou.command;

import main.java.Hudou.task.TaskList;

public class ExitCommand extends Command {
    public static final String chatbotExit =  '\n' + ChatBot.lineSeparator + '\n' +
            "Bye. Hope to see you again soon!\n" +
            ChatBot.lineSeparator + '\n';

    //end program when chatbot recognise a command input as "bye"
    public static void endSession() {
        System.out.println(chatbotExit);
    }

    @Override
    public void execute(TaskList taskList) {
        endSession();
    }
}

