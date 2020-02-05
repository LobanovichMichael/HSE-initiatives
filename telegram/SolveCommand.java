package ru.misha.telegram;

public class SolveCommand implements Command{
    String chatID;
    String textMessage;

    public SolveCommand(String chatID, String messageText) {
        this.chatID = chatID;
        this.textMessage = messageText;
    }

    @Override
    public String Run() {
        String botMessage = "";
        int taskNumber = Data.taskMemory.get(chatID);
        String numberString = Data.themeMemory.get(chatID);
        int numberOfTheme = Integer.valueOf(numberString);
        botMessage = botMessage + Data.themes.get(numberOfTheme - 1).themeTasks.get(taskNumber).taskSolve;
        return botMessage;
    }
}
